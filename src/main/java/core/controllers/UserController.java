package core.controllers;

import core.exceptions.BadRequestException;
import core.requestModels.LoginRequestModel;
import core.requestModels.SignUpRequestModel;
import core.responseModels.UserLoginResponseModel;
import core.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {
    @Autowired
    private SecurityService securityService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody @Valid SignUpRequestModel model) throws BadRequestException {
        securityService.signUpUser(model);
    }

    @PostMapping("/logIn")
    public UserLoginResponseModel login(@RequestBody @Valid LoginRequestModel loginRequestModel) {
        return securityService.loginUser(loginRequestModel);
    }

    @PostMapping("/logOut")
    public void logOut(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @PostMapping("/confirm/{uuid}")
    public void activateUser(@PathVariable UUID uuid) {

    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_USER') or hasPermission(#permission,'write')")
    public void test(@RequestParam String permission) {
        System.out.println("Ok");
    }
}
