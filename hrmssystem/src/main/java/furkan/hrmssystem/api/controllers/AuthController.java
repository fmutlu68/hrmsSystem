package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.AuthService;
import furkan.hrmssystem.core.entities.UserDtoForLogin;
import furkan.hrmssystem.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public DataResult<Object> login(@RequestBody UserDtoForLogin userDtoForLogin) {
        System.out.println(userDtoForLogin);
        return this.authService.loginUser(userDtoForLogin);
    }
}
