package furkan.hrmssystem.api.controllers;

import java.util.List;

import furkan.hrmssystem.core.entities.UserDtoForAccountValidation;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import furkan.hrmssystem.business.abstracts.UserService;
import furkan.hrmssystem.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return userService.getAll();
	}

	@PostMapping("/validate")
	public Result validate(@RequestBody UserDtoForAccountValidation user) throws Exception {
		return userService.validateAccount(user);
	}
}
