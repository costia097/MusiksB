package core.enums;

import lombok.Getter;

@Getter
public enum PermissionNames {
    WATCH_PERMISSION_NAME("watch"),
    READ_PERMISSION_NAME("read"),
    WRITE_PERMISSION_NAME("write");

    PermissionNames(String name) {
        this.name = name;
    }
    private String name;
}
