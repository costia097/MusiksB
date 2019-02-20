package core.services;

import core.entities.Permission;
import core.entities.Role;
import core.repositories.PermissionRepository;
import core.repositories.RoleRepository;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static core.enums.PermissionNames.READ_PERMISSION_NAME;
import static core.enums.PermissionNames.WATCH_PERMISSION_NAME;
import static core.enums.PermissionNames.WRITE_PERMISSION_NAME;
import static core.enums.RoleNames.ROLE_INACTIVE;
import static core.enums.RoleNames.ROLE_USER;

@Service
public class PermissionAndRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    private List<String> permissionsForInactiveUser = Collections.singletonList(
            WATCH_PERMISSION_NAME.getName()
    );
    private List<String> permissionsForActiveUser = Arrays.asList(WATCH_PERMISSION_NAME.getName(),
            READ_PERMISSION_NAME.getName(),
            WRITE_PERMISSION_NAME.getName()
    );

    Tuple2<List<Permission>, List<Role>> resolvePermissionsAndRolesForInactiveUser() {
        return Tuple.of(
                permissionRepository.findByNames(permissionsForInactiveUser)
                        .getOrElse(Collections.emptyList()),
                roleRepository.findByNames(Collections.singletonList(ROLE_INACTIVE.name()))
                        .getOrElse(Collections.emptyList())
        );
    }

    Tuple2<List<Permission>, List<Role>> resolvePermissionsAndRolesForActiveUser() {
        return Tuple.of(
                permissionRepository.findByNames(permissionsForActiveUser)
                        .getOrElse(Collections.emptyList()),
                roleRepository.findByNames(Collections.singletonList(ROLE_USER.name()))
                        .getOrElse(Collections.emptyList()));
    }

}
