package furkan.hrmssystem.business.validationRules.concretes;

import furkan.hrmssystem.business.validationRules.abstracts.MainValidator;
import furkan.hrmssystem.core.utilities.errors.ValidationError;
import furkan.hrmssystem.entities.concretes.EmployeeUser;

import java.util.Date;

public class EmployeeUserValidator implements MainValidator<EmployeeUser> {
    @Override
    public ValidationError validate(EmployeeUser entity) {
        var validation = validateFirstName(entity.getFirstName());
        if (validation != null){
            return validation;
        }
        validation = validateLastName(entity.getLastName());
        if (validation != null){
            return validation;
        }
        validation = validateIdentityNo(entity.getIdentityNo());
        if (validation != null){
            return validation;
        }
        validation = validateBirthDate(entity.getBirthDate());
        if (validation != null){
            return validation;
        }
        return null;
    }

    @Override
    public boolean support(Class<?> clasz) {
        return EmployeeUser.class.equals(clasz);
    }

    private ValidationError validateFirstName(String firstname){
        if (firstname == null){
            return new ValidationError("İsim Boş Geçilemez", "İsim");
        }
        return null;
    }

    private ValidationError validateLastName(String lastname){
        if (lastname == null){
            return new ValidationError("Soyisim Boş Geçilemez", "Soyisim");
        }
        return null;
    }

    private ValidationError validateBirthDate(Date birthDate){
        if (birthDate == null){
            return new ValidationError("Doğum Tarihi Boş Geçilemez", "Doğum Tarihi");
        }
        return null;
    }

    private ValidationError validateIdentityNo(String identityNo){
        if (identityNo == null){
            return new ValidationError("Kimlik No Boş Geçilemez", "Kimlik No");
        }
        return null;
    }
}
