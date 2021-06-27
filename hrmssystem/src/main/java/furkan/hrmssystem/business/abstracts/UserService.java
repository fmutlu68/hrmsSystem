package furkan.hrmssystem.business.abstracts;

import java.util.List;

import furkan.hrmssystem.core.entities.UserDtoForAccountValidation;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.User;

public interface UserService {
	DataResult<List<User>> getAll();
	DataResult<User> getById(int id);
	DataResult<User> getByMail(String mail);
	DataResult<User> getByMailAndPassword(String mail, String password);
	Result add(User user) throws Exception;
	Result validateAccount(UserDtoForAccountValidation user) throws Exception;
	Object checkUserIsExistByMail(String mail) throws Exception;
}
