package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.EmployerUserService;
import furkan.hrmssystem.business.abstracts.UserOperationService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import furkan.hrmssystem.entities.concretes.UserOperation;
import furkan.hrmssystem.entities.dtos.UserOperationDtoForAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/useroperations/")
@CrossOrigin
public class UserOperationController {
    private final UserOperationService service;
    private final EmployerUserService employerUserService;

    @Autowired
    public UserOperationController(UserOperationService service, EmployerUserService employerUserService) {
        this.service = service;
        this.employerUserService = employerUserService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<UserOperation>>> getAll() {
        return ResponseEntity.ok(this.service.getOperations());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody UserOperationDtoForAction operation) {
        if (operation.getNewUser().getUserId() > 0){
            operation.getNewUser().setUserId(0);
        }
        operation.getOldUser().setOld(true);
        var newUserResult = this.employerUserService.register(operation.getNewUser());
        var oldUserResult = this.employerUserService.update(operation.getOldUser());
        if (newUserResult.isSuccess() && oldUserResult.isSuccess()){
            var result = this.service.addOperation(new UserOperation(operation.getOperationTypeId(), newUserResult.getData().getUserId(), operation.getOldUser().getUserId(), false));
            if (result.isSuccess()){
                return ResponseEntity.ok(result);
            }else {
                return new ResponseEntity(result, HttpStatus.NOT_FOUND);
            }
        }else {
            return new ResponseEntity(new ErrorResult(newUserResult.getMessage()), HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/isactivated/{id}")
    public Result isActivated(@PathVariable("id") int oldUserId) {
        var user = this.employerUserService.getById(oldUserId);
        return this.service.isActivated(user.getData().getUserId());
    }

    @GetMapping("/getallnoactivated")
    public DataResult<List<UserOperation>> getAllNoActivated() {
        return this.service.getOperationsNoActivated();
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<Result> activate(@PathVariable int id) {
        var result = this.service.activateOperation(id);
        if (result.isSuccess()){
            var operation = result.getData();
            operation.getOldUser().setOld(true);
            var oldUserResult = this.employerUserService.update(operation.getOldUser());
            return ResponseEntity.ok(new SuccessResult(result.getMessage()));
        }else {
            return new ResponseEntity<Result>(result, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deactivate/{id}")
    public ResponseEntity<Result> deactivate(@PathVariable int id) {
        var result = this.service.deactivateOperation(id);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }else {
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
    }
}
