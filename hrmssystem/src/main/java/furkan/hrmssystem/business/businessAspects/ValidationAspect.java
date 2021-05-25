package furkan.hrmssystem.business.businessAspects;

import furkan.hrmssystem.business.validationRules.abstracts.MainValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import furkan.hrmssystem.core.utilities.errors.ValidationError;
import furkan.hrmssystem.business.businessAnnotations.Validate;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

	// Aşağıda @Before İfadesiyle başlayan Expression Concretes Package'ı altında bulundan ve Methodunun İmzasında @Validate annotationları bulunan nmethodları ifade ediyor.
	// @Before ifadesiyle Bu methodun method çalışmadan önce çalıştırılacağını ifade ediyoruz.
	@Before("execution (* furkan.hrmssystem.business.concretes.*.*(..)) && @annotation(furkan.hrmssystem.business.businessAnnotations.Validate)")
	public void onBefore(JoinPoint joinPoint) throws ValidationError{
		Object methodArg = joinPoint.getArgs()[0];
		Validate validateAnnotation = getValidateAnnotation((MethodSignature) joinPoint.getSignature());
		MainValidator validator = validateAnnotation.currentValidation().getValidator();
		executeValidator(validator, methodArg);
	}

	private void executeValidator(MainValidator validator, Object methodArg) {
		if (validator.support(methodArg.getClass())){
			ValidationError error = validator.validate(methodArg);
			if (error != null){
				throw error;
			}
		}
	}

	private Validate getValidateAnnotation(MethodSignature signature){
		return AnnotationUtils.getAnnotation(signature.getMethod(), Validate.class);
	}
}