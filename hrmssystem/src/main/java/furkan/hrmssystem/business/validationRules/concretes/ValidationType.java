package furkan.hrmssystem.business.validationRules.concretes;


import furkan.hrmssystem.core.utilities.errors.EnumNotFoundError;
import furkan.hrmssystem.business.validationRules.abstracts.MainValidator;
import furkan.hrmssystem.entities.concretes.EmployeeUser;

public enum ValidationType {
	USERVALIDATOR,
	EMPLOYEEUSERVALIDATOR,
	EMPLOYERUSERVALIDATOR;

	public final MainValidator getValidator() {
		switch (this) {
		case EMPLOYEEUSERVALIDATOR:
			return new EmployeeUserValidator();
		case USERVALIDATOR:
			return new UserValidator();
		case EMPLOYERUSERVALIDATOR:
			return new EmployeeUserValidator();
		default:
		    throw new EnumNotFoundError(this.name(), "Bu Doğrulama Tipi Bulunamadı.");
		}
	}
}
