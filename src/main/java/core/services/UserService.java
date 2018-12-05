package core.services;

import core.entities.Permission;
import core.entities.Role;
import core.repositories.UserRepository;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionAndRoleService permissionAndRoleService;

    @Transactional
    public void activateUser(UUID uuid) {
        Tuple2<List<Permission>, List<Role>> tupleOfRolesAndPermissions = permissionAndRoleService.resolvePermissionsAndRolesForActiveUser();

        userRepository.findByUuid(uuid)
                .filter(user -> !user.getActive())
                .peek(user -> user.setActive(true))
                .peek(user -> user.getPermissions().clear())
                .peek(user -> user.getRoles().clear())
                .peek(user -> user.setRoles(new HashSet<>(tupleOfRolesAndPermissions._2)))
                .peek(user -> user.setPermissions(new HashSet<>(tupleOfRolesAndPermissions._1)))
                .peek(user -> userRepository.update(user));
    }
}
