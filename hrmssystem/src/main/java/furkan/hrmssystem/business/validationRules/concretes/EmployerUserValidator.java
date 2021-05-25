package furkan.hrmssystem.business.validationRules.concretes;

import furkan.hrmssystem.business.validationRules.abstracts.MainValidator;
import furkan.hrmssystem.core.utilities.errors.ValidationError;
import furkan.hrmssystem.entities.concretes.EmployerUser;

public class EmployerUserValidator implements MainValidator<EmployerUser> {
    @Override
    public ValidationError validate(EmployerUser entity) {
        var validation = validateCompanyName(entity.getCompanyName());
        if (validation != null){
            return validation;
        }
        validation = validateCompanyWebSite(entity.getCompanyWebSite());
        if (validation != null){
            return validation;
        }
        return validateCompanyPhone(entity.getCompanyPhone());
    }

    @Override
    public boolean support(Class<?> clasz) {
        return EmployerUser.class.equals(clasz);
    }

    private ValidationError validateCompanyName(String companyName){
        if (companyName == null){
            return new ValidationError("Şirket Adı Boş Geçilemez.", "Şirket Adı");
        }
        return null;
    }

    private ValidationError validateCompanyWebSite(String companyWebSite){
        if (companyWebSite == null){
            return new ValidationError("Şirket Web Sitesi Boş Geçilemez.", "Şirket Web Sitesi");
        }
        return null;
    }

    private ValidationError validateCompanyPhone(String companyPhone){
        if (companyPhone == null){
            return new ValidationError("Şirket Telefonu Sitesi Boş Geçilemez.", "Şirket Telefonu");
        }
        return null;
    }
}
