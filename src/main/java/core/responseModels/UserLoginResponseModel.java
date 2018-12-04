package core.responseModels;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserLoginResponseModel {
    private String userName;
    private Set<String> roles;
    private Set<String> permissions;
}
