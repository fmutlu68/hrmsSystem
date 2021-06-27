package furkan.hrmssystem.business.abstracts;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.EmployerUser;

public interface EmployerUserService {
	DataResult<List<EmployerUser>> getAll();
	DataResult<EmployerUser> getById(int id);
	DataResult<EmployerUser> checkUserIsEmployer(String mail);
	DataResult<EmployerUser> register(EmployerUser user);
	Result delete(EmployerUser user);
	Result update(EmployerUser user);
}
