package furkan.hrmssystem.business.concretes;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.EmployerUserService;
import furkan.hrmssystem.dataAccess.abstracts.EmployerUserDao;
import furkan.hrmssystem.entities.concretes.EmployerUser;

@Service
public class EmployerUserManager implements EmployerUserService{

	private EmployerUserDao employerUserDao;
	
	@Autowired
	public EmployerUserManager(EmployerUserDao employerUserDao) {
		super();
		this.employerUserDao = employerUserDao;
	}

	@Override
	public DataResult<List<EmployerUser>> getAll() {
		return new SuccessDataResult<List<EmployerUser>>(employerUserDao.findAll());
	}

	@Override
	public DataResult<EmployerUser> getById(int id) {
		return new SuccessDataResult<EmployerUser>(employerUserDao.findById(id).get());
	}

	@Override
	public DataResult<EmployerUser> checkUserIsEmployer(String mail) {
		var result = this.employerUserDao.getByEmailIsEmployer(mail);
		if (result != null && !result.isOld()){
			return new SuccessDataResult<EmployerUser>(result);
		}
		return new ErrorDataResult<EmployerUser>("Kullanıcı Bulunamadı.");
	}

	@Override
	public DataResult<EmployerUser> register(EmployerUser user) {
		user.setOld(false);
		return new SuccessDataResult<EmployerUser>(employerUserDao.save(user), "Kayıt Başarılı");
	}

	@Override
	public Result delete(EmployerUser user) {
		employerUserDao.delete(user);
		return new SuccessResult("Silme İşlemi Başarılı.");
	}

	@Override
	public Result update(EmployerUser user) {
		this.employerUserDao.save(user);
		return new SuccessResult("Kullanıcı Güncellendi.");
	}

}
