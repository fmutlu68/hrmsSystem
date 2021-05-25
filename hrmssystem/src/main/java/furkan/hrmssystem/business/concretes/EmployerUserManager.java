package furkan.hrmssystem.business.concretes;

import java.util.List;

import furkan.hrmssystem.business.businessAnnotations.Validate;
import furkan.hrmssystem.business.validationRules.concretes.ValidationType;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
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
	@Validate(currentValidation = ValidationType.EMPLOYERUSERVALIDATOR)
	public DataResult<EmployerUser> register(EmployerUser user) {
		return new SuccessDataResult<EmployerUser>(employerUserDao.save(user), "Kayıt Başarılı");
	}

	@Override
	public Result delete(EmployerUser user) {
		employerUserDao.delete(user);
		return new SuccessResult("Silme İşlemi Başarılı.");
	}

}
