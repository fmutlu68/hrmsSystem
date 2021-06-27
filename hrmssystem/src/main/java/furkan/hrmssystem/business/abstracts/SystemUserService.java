package furkan.hrmssystem.business.abstracts;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.SystemUser;

public interface SystemUserService {
	DataResult<List<SystemUser>> getAll();
	DataResult<SystemUser> getById(int id);
	DataResult<SystemUser> checkUserIsSystemUser(String mail);
	DataResult<SystemUser> updateUser(SystemUser user);
}
