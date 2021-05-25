package furkan.hrmssystem.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.SystemUserService;
import furkan.hrmssystem.dataAccess.abstracts.SystemUserDao;
import furkan.hrmssystem.entities.concretes.SystemUser;

@Service
public class SystemUserManager implements SystemUserService{

	private SystemUserDao systemUserDao;
	
	@Autowired
	public SystemUserManager(SystemUserDao systemUserDao) {
		super();
		this.systemUserDao = systemUserDao;
	}

	@Override
	public List<SystemUser> getAll() {
		return systemUserDao.findAll();
	}

	@Override
	public SystemUser getById(int id) {
		return systemUserDao.findById(id).get();
	}

}
