package core.services;

import core.entities.Permission;
import core.entities.Role;
import core.entities.User;
import core.exceptions.BadRequestException;
import core.repositories.UserRepository;
import core.requestModels.LoginRequestModel;
import core.requestModels.SignUpRequestModel;
import core.responseModels.UserLoginResponseModel;
import core.security.MusiksAuthenticationToken;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static core.convertors.UserConvertor.convertToUserEntityFromSignUpRequestModel;

@Service
public class SecurityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionAndRoleService permissionAndRoleService;
    @Autowired
    private AddressService addressService;

    @Transactional
    public void signUpUser(SignUpRequestModel model) throws BadRequestException {
        if (isUserWithGivenLoginExist(model.getLogin())) {
            throw new BadRequestException("User with given login is already exist");
        }

        User entityUser = convertToUserEntityFromSignUpRequestModel(model);

        Tuple2<List<Permission>, List<Role>> tupleOfRolesAndPermissions = permissionAndRoleService.resolvePermissionsAndRolesForInactiveUser();

        entityUser.setPermissions(new HashSet<>(tupleOfRolesAndPermissions._1));
        entityUser.setRoles(new HashSet<>(tupleOfRolesAndPermissions._2));

        addressService.addAddressToUserEntity(entityUser, model.getAddressLine(), model.getCountry());

        userRepository.save(entityUser);
    }

    @Transactional
    public UserLoginResponseModel loginUser(LoginRequestModel loginRequestModel) {
        return userRepository.findByLogin(loginRequestModel.getLogin())
                .filter(user -> isValidUserPassword(user, loginRequestModel.getPassword()))
                .filter(User::getActive)
                .map(this::extractUserLoginResponseModelFromUserEntity)
                .peek(userLoginResponseModel -> executeLoginUser(userLoginResponseModel, loginRequestModel))
                .getOrNull();
    }

    private boolean isValidUserPassword(User user, String userInput) {
        return user.getPassword().equals(userInput);
    }

    private UserLoginResponseModel extractUserLoginResponseModelFromUserEntity(User user) {
        UserLoginResponseModel userLoginResponseModel = new UserLoginResponseModel();

        userLoginResponseModel.setUserName(user.getLogin());

        Set<String> permissionsConverted = user.getPermissions()
                .stream()
                .map(Permission::getName)
                .collect(Collectors.toSet());

        userLoginResponseModel.setPermissions(permissionsConverted);

        Set<String> rolesConverted = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        userLoginResponseModel.setRoles(rolesConverted);

        return userLoginResponseModel;
    }

    private void executeLoginUser(UserLoginResponseModel userLoginResponseModel, LoginRequestModel loginRequestModel) {
        SecurityContext context = SecurityContextHolder.getContext();

        List<SimpleGrantedAuthority> rolesConverted = userLoginResponseModel.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        MusiksAuthenticationToken authenticationToken = new MusiksAuthenticationToken(rolesConverted);

        authenticationToken.setLogin(loginRequestModel.getLogin());
        authenticationToken.setPassword(loginRequestModel.getPassword());
        authenticationToken.setPermissions(userLoginResponseModel.getPermissions());

        context.setAuthentication(authenticationToken);
    }

    private boolean isUserWithGivenLoginExist(String login) {
        return userRepository.findByLogin(login).getOrNull() != null;
    }
}
