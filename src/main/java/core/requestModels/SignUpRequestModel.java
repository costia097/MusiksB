package core.requestModels;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequestModel {
    @NotNull
    private String firstName;
    @NotNull
    @Size(min = 3)
    private String lastName;
    @NotNull
    @Size(min = 3)
    @Size(max = 15)
    private String login;
    @NotNull
    @Size(min = 3)
    @Size(max = 25)
    private String password;
    @NotNull
//    @Email
    private String email;
    @NotNull
    private String gender;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private String country;
    private String addressLine;
}
