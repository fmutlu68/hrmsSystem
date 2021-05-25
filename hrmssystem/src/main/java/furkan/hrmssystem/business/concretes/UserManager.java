package furkan.hrmssystem.business.concretes;

import java.util.List;
import java.util.Random;

import furkan.hrmssystem.business.abstracts.EmailService;
import furkan.hrmssystem.business.businessAnnotations.Validate;
import furkan.hrmssystem.business.validationRules.concretes.ValidationType;
import furkan.hrmssystem.core.entities.EmailMessage;
import furkan.hrmssystem.core.entities.UserDtoForAccountValidation;
import furkan.hrmssystem.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.UserService;
import furkan.hrmssystem.dataAccess.abstracts.UserDao;
import furkan.hrmssystem.entities.concretes.User;

@Service
public class UserManager implements UserService{
	private UserDao userDao;
	private EmailService mailService;
	
	@Autowired
	public UserManager(UserDao userDao, EmailService mailService) {
		super();
		this.userDao = userDao;
		this.mailService = mailService;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(userDao.findAll(), "Listeleme Başarılı");
	}

	@Override
	public DataResult<User> getById(int id) {
		return new SuccessDataResult<User>(userDao.findById(id).get(), "Listeleme Başarılı");
	}

	@Override
	@Validate(currentValidation = ValidationType.USERVALIDATOR)
	public Result add(User user) throws Exception {
		checkUserIsExistByMail(user.getEMail());
		var userRegistered = userDao.save(user);
		if (userRegistered.getId() >= 0){
			EmailMessage message = new EmailMessage();
			String accountValidationCode = generateAccountValidationCode();

			message.setSubject("Hrms Sistemi Üyelik Bilgilendirmesi");
			message.setContent("Merhaba,\nHrms Sistemine Hoş Geldiniz. Üyeliğinizin Geçerli Olması İçin Bu 6 Haneli Şifreyi ("+accountValidationCode+") /api/users/validate Linkine Post İşlemi Atarak Gönderiniz. İyi Günler.");
			message.setFrom("no-reply@hrmssystem.com");
			message.setTo(user.getEMail());
			mailService.sendMail(message);

			user.setStatus(false);
			user.setValidationCode(accountValidationCode);
			userDao.save(user);

			return new SuccessResult("Kayıt İşlemi Başarılı. Hesabınızın Aktif Edilmesi İçin Emailinize Gönderilen Şifreyi Girmeniz Gerekmektedir.");
		}
		return new ErrorResult();
	}

	@Override
	public Result validateAccount(UserDtoForAccountValidation user){
		var userToValidate = userDao.findAll()
				.parallelStream()
				.filter(u -> u.getEMail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
				.findFirst().orElse(null);
		if (userToValidate == null){
			return new ErrorResult("Hesap Bulunamadı.");
		}else if (userToValidate.getValidationCode().equals(user.getValidationCode())){
			userToValidate.setStatus(true);
			userDao.save(userToValidate);
			return new SuccessResult("Hesabınız Doğrulandı.");
		}
		return new ErrorResult("Hesabınız Doğrulanamadı. Girdiğiniz Doğrulama Kodu Yanlış.");
	}

	@Override
	public Object checkUserIsExistByMail(String mail) throws Exception {
		var users = userDao.findAll();
		for (User user: users){
			if (user.getEMail().equals(mail)){
				throw new Exception("Girilen Maile Ait Bir Kullanıcı Zaten Var.");
			}
		}
		return null;
	}
	private String generateAccountValidationCode(){
		Random random = new Random();
		return ""+getNumberSmaller10(random)+""+getNumberSmaller10(random)+""+getNumberSmaller10(random)+""+getNumberSmaller10(random)+""+getNumberSmaller10(random)+""+getNumberSmaller10(random);
	}

	// Yukarıdaki Kodun Daha Az Alana Sığabilmesi İçin Alttaki Metodun İsmini Tam Yazmadım.
	private int getNumberSmaller10(Random random){
		return random.nextInt(10);
	}
}
