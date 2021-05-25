package furkan.hrmssystem.business.validationRules.abstracts;


import furkan.hrmssystem.core.utilities.errors.ValidationError;

public interface MainValidator<T> {
	ValidationError validate(T entity);
	boolean support(Class<?> clasz);
}	
