package furkan.hrmssystem.business.concretes;

import java.rmi.RemoteException;
import java.util.List;

import furkan.hrmssystem.adapters.abstracts.UserCheckService;
import furkan.hrmssystem.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.SystemUserService;
import furkan.hrmssystem.dataAccess.abstracts.SystemUserDao;
import furkan.hrmssystem.entities.concretes.SystemUser;

@Service
public class SystemUserManager implements SystemUserService{

	private SystemUserDao systemUserDao;
	private UserCheckService userCheckService;
	
	@Autowired
	public SystemUserManager(SystemUserDao systemUserDao, UserCheckService userCheckService) {
		super();
		this.systemUserDao = systemUserDao;
		this.userCheckService = userCheckService;
	}

	@Override
	public DataResult<List<SystemUser>> getAll() {
		return new SuccessDataResult<List<SystemUser>>(systemUserDao.findAll());
	}

	@Override
	public DataResult<SystemUser> getById(int id) {
		var result = systemUserDao.findById(id);
		if (result.isPresent()){
			return new SuccessDataResult<SystemUser>(result.get());
		}
		return new ErrorDataResult<SystemUser>("Kullanıcı Bulunamadı.");
	}

	@Override
	public DataResult<SystemUser> checkUserIsSystemUser(String mail) {
		var result = this.systemUserDao.getByEmailIsSystemUser(mail);
		if (result != null){
			return new SuccessDataResult<SystemUser>(result);
		}
		return new ErrorDataResult<SystemUser>("Kullanıcı Bulunamadı.");
	}

	@Override
	public DataResult<SystemUser> updateUser(SystemUser user) {
		var result = this.systemUserDao.findById(user.getUserId());
		if (result.isPresent()){
			try{
				if (userCheckService.checkIfRealPerson(Long.parseLong(user.getIdentityNo()), user.getFirstName(), user.getLastName(), user.getBirthDate())){
					this.systemUserDao.save(user);
					return new SuccessDataResult<SystemUser>(result.get(), "Güncelleme Başarılı.");
				}else {
					return new ErrorDataResult<SystemUser>("Kimlik Doğrulama Başarısız");
				}
			}catch(RemoteException ex) {
				return new ErrorDataResult<SystemUser>("Hata Oluştu: " + ex.getMessage());
			}
		}
		return new ErrorDataResult<SystemUser>("Kullanıcı Bulunamadı.");
	}

}
