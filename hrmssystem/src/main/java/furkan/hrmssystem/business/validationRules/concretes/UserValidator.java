package furkan.hrmssystem.business.validationRules.concretes;

import furkan.hrmssystem.business.validationRules.abstracts.MainValidator;
import furkan.hrmssystem.core.utilities.errors.ValidationError;
import furkan.hrmssystem.entities.concretes.User;

public class UserValidator implements MainValidator<User> {
    private ValidationError validation;
    @Override
    public ValidationError validate(User entity) {
        validation = validatePassword(entity.getPassword());
        if (validation != null){
            return validation;
        }
        validation = validateEmail(entity.getEMail());
        if (validation != null){
            return validation;
        }
        validation = validateUserId(entity.getUserId());
        return validation;
    }

    private ValidationError validateUserId(int userId) {
        if (userId < 0){
            return new ValidationError("Kullanıcı Numarası Yanlış", "Kullanıcı");
        }
        return null;
    }

    private ValidationError validateEmail(String email){
        if (email == null){
            return new ValidationError("E-Posta Boş Geçilemez", "E-Posta");
        }
        return null;
    }

    private ValidationError validatePassword(String password){
        if (password == null){
            return new ValidationError("Şifre Boş Geçilemez", "Şifre");
        }
        return null;
    }

    @Override
    public boolean support(Class<?> clasz) {
        return User.class.equals(clasz);
    }
}
