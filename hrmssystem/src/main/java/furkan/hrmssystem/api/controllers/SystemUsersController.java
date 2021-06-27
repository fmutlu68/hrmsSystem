package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.SystemUserService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.entities.concretes.SystemUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemusers")
@CrossOrigin
public class SystemUsersController {
    private final SystemUserService systemUserService;

    public SystemUsersController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PostMapping("/update")
    public DataResult<SystemUser> update(@RequestBody SystemUser user) {
        return this.systemUserService.updateUser(user);
    }
}
