package core.controllers;

import core.exceptions.BadRequestException;
import core.requestModels.LoginRequestModel;
import core.requestModels.SignUpRequestModel;
import core.responseModels.UserLoginResponseModel;
import core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody @Valid SignUpRequestModel model) throws BadRequestException {
        userService.signUpUser(model);
    }

    @PostMapping("/logIn")
    public UserLoginResponseModel login(@RequestBody @Valid LoginRequestModel loginRequestModel) {
        return userService.loginUser(loginRequestModel);
    }

    @PostMapping("/logOut")
    public void logOut(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PostMapping("/confirm/{uuid}")
    public void activateUser(@PathVariable UUID uuid) {

    }
}
