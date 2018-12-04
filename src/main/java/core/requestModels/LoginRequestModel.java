package core.requestModels;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequestModel {
    @NotNull
    @Size(min = 3)
    @Size(max = 15)
    private String login;
    @NotNull
    @Size(min = 3)
    @Size(max = 25)
    private String password;
}
