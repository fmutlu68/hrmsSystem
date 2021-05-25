package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.EmployeeUserService;
import furkan.hrmssystem.business.abstracts.UserService;
import furkan.hrmssystem.core.entities.EmployeeUserDtoForRegister;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.EmployeeUser;
import furkan.hrmssystem.entities.concretes.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesUserController {
    private EmployeeUserService employeeUserService;
    private UserService userService;

    @Autowired
    public EmployeesUserController(EmployeeUserService employeeUserService, UserService userService) {
        this.employeeUserService = employeeUserService;
        this.userService = userService;
    }

    @GetMapping("/getall")
    public DataResult<List<EmployeeUser>> getAll(){
        return this.employeeUserService.getAll();
    }

    @SneakyThrows
    @PostMapping("/add")
    public Result add(EmployeeUserDtoForRegister user) {
        DataResult<EmployeeUser> employeeUser = employeeUserService.register(new EmployeeUser(user.getFirstname(), user.getLastname(), user.getBirthDate(), user.getIdentityNo(), user.getEmail()));
        if (employeeUser.isSuccess()){
            try {
                return userService.add(new User(employeeUser.getData().getUserId(), user.getEmail(), user.getPassword()));
            }catch (Exception error){
                employeeUserService.delete(employeeUser.getData());
                return new ErrorResult(error.getMessage());
            }
        }else{
            return new ErrorResult(employeeUser.getMessage());
        }
    }
}
