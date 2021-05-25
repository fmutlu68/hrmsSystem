package furkan.hrmssystem.business.abstracts;

import java.util.List;

import furkan.hrmssystem.entities.concretes.SystemUser;

public interface SystemUserService {
	List<SystemUser> getAll();
	SystemUser getById(int id);
}
