package furkan.hrmssystem.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.EmployeeUser;

public interface EmployeeUserService {
	DataResult<List<EmployeeUser>> getAll();
	DataResult<EmployeeUser> getById(int id);
	DataResult<EmployeeUser> register(EmployeeUser user) throws RemoteException, Exception;
	Result delete(EmployeeUser user);
	void checkUserIsExistByIdentityNo(String tcno) throws Exception;
}
