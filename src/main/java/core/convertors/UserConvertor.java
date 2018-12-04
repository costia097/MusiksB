package core.convertors;

import core.entities.User;
import core.requestModels.SignUpRequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UserConvertor {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static User convertToUserEntityFromSignUpRequestModel(SignUpRequestModel signUpRequestModel) {
        User user = new User();

        user.setLogin(signUpRequestModel.getLogin());
        user.setUuid(UUID.randomUUID());
        user.setPassword(signUpRequestModel.getPassword());
        user.setEmail(signUpRequestModel.getEmail());
        user.setFirstName(signUpRequestModel.getFirstName());
        user.setLastName(signUpRequestModel.getLastName());
        user.setActive(false);
        user.setGender(signUpRequestModel.getGender());
        user.setDateOfBirthday(LocalDate.parse(signUpRequestModel.getDateOfBirth(), formatter));

        return user;
    }
}
