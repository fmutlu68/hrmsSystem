package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.*;
import furkan.hrmssystem.core.entities.UserDtoForLogin;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private UserService userService;
    private EmployeeUserService employeeUserService;
    private EmployerUserService employerUserService;
    private SystemUserService systemUserService;

    @Autowired
    public AuthManager(UserService userService, EmployeeUserService employeeUserService, EmployerUserService employerUserService, SystemUserService systemUserService) {
        this.userService = userService;
        this.employeeUserService = employeeUserService;
        this.employerUserService = employerUserService;
        this.systemUserService = systemUserService;
    }

    @Override
    public DataResult<Object> loginUser(UserDtoForLogin user) {
        var userToLogin = this.userService.getByMailAndPassword(user.getEmail(), user.getPassword());
        if (userToLogin.getData() == null){
            return new ErrorDataResult<Object>("E-Posta Veya Şifre Hatalı");
        }
        var userIsEmployee = this.employeeUserService.checkUserIsEmployer(userToLogin.getData().getEmail());
        if (userIsEmployee.isSuccess()){
            return new SuccessDataResult<Object>(userIsEmployee.getData(), "Giriş Başarılı");
        }
        var userIsEmployer = this.employerUserService.checkUserIsEmployer(userToLogin.getData().getEmail());
        if (userIsEmployer.isSuccess()){
            return new SuccessDataResult<Object>(userIsEmployer.getData(), "Giriş Başarılı");
        }
        var userIsSystem = this.systemUserService.checkUserIsSystemUser(userToLogin.getData().getEmail());
        if (userIsSystem.isSuccess()){
            return new SuccessDataResult<Object>(userIsSystem.getData(), "Giriş Başarılı");
        }
        return new ErrorDataResult<Object>("Giriş Başarısız.");
    }
}
