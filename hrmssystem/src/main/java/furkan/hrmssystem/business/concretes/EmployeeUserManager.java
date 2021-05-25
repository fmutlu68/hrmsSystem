package furkan.hrmssystem.business.concretes;

import java.rmi.RemoteException;
import java.util.List;

import furkan.hrmssystem.adapters.abstracts.UserCheckService;
import furkan.hrmssystem.business.abstracts.UserService;
import furkan.hrmssystem.business.businessAnnotations.Validate;
import furkan.hrmssystem.business.validationRules.concretes.ValidationType;
import furkan.hrmssystem.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.EmployeeUserService;
import furkan.hrmssystem.dataAccess.abstracts.EmployeeUserDao;
import furkan.hrmssystem.entities.concretes.EmployeeUser;

@Service
public class EmployeeUserManager implements EmployeeUserService{

	private EmployeeUserDao employeeUserDao;
	private UserService userService;
	private UserCheckService userCheckService;
	
	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	public EmployeeUserManager(EmployeeUserDao employeeUserDao, UserService userService, UserCheckService userCheckService) {
		super();
		this.employeeUserDao = employeeUserDao;
		this.userService = userService;
		this.userCheckService = userCheckService;
	}

	@Override
	public DataResult<List<EmployeeUser>> getAll() {
		return new SuccessDataResult(employeeUserDao.findAll(), "Listeleme Başarılı");
	}

	@Override
	public DataResult<EmployeeUser> getById(int id) {
		return new SuccessDataResult( employeeUserDao.findById(id).get(), "Listeleme Başarılı");
	}

	@Override
	@Validate(currentValidation = ValidationType.EMPLOYEEUSERVALIDATOR)
	public DataResult<EmployeeUser> register(EmployeeUser user) throws Exception {
		checkUserIsExistByIdentityNo(user.getIdentityNo());
		if (checkPerson(user)){
			return new SuccessDataResult<EmployeeUser>(employeeUserDao.save(user), "Kayıt Başarılı");
		}else{
			return new ErrorDataResult<EmployeeUser>("Kimlik Doğrulaması Başarısız.");
		}

	}

	@Override
	public Result delete(EmployeeUser user) {
		employeeUserDao.delete(user);
		return new SuccessResult("Kulanıcı Silindi.");
	}

	@Override
	public void checkUserIsExistByIdentityNo(String tcno) throws Exception {
		var users = employeeUserDao.findAll();
		for (EmployeeUser user : users){
			if (user.getIdentityNo().equals(tcno)){
				throw new Exception("Girilen Kimlik Numarası İle Zaten Kayıt Olunmuş.");
			}
		}
	}

	private boolean checkPerson(EmployeeUser user) throws RemoteException {
		return userCheckService
				.checkIfRealPerson(
						Long.parseLong(
								user.getIdentityNo()
						),
						user.getFirstName().toUpperCase(),
						user.getLastName().toUpperCase(),
						user.getBirthDate()
				);
	}
}
