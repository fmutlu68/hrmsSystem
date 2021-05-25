package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.EmployerUserService;
import furkan.hrmssystem.business.abstracts.UserService;
import furkan.hrmssystem.core.entities.EmployerUserDtoForRegister;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.EmployerUser;
import furkan.hrmssystem.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployersUserController {
    private EmployerUserService employerUserService;
    private UserService userService;

    @Autowired
    public EmployersUserController(EmployerUserService employerUserService, UserService userService) {
        this.employerUserService = employerUserService;
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<EmployerUser>> getAll(){
        return employerUserService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody EmployerUserDtoForRegister user){
        DataResult<EmployerUser> result = employerUserService.register(new EmployerUser(user.getCompanyName(), user.getCompanyWebSite(), user.getCompanyPhone(), user.getEmail()));
        if (result.isSuccess()){
            try {
                return userService.add(new User(result.getData().getUserId(), user.getEmail(), user.getPassword()));
            } catch (Exception e) {
                employerUserService.delete(result.getData());
                return new ErrorResult(e.getMessage() + "\n\n" + e.getLocalizedMessage());
            }
        }else{
            return new ErrorResult(result.getMessage());
        }
    }
}
