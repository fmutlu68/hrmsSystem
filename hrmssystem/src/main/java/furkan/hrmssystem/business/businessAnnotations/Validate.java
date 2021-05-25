package furkan.hrmssystem.business.businessAnnotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import furkan.hrmssystem.business.validationRules.concretes.ValidationType;
import org.springframework.stereotype.Component;


@Target(METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Validate {
	public ValidationType currentValidation();
}

// BU ANNOTATİON SAYESİNDE GEREKLİ VALIDATOR SINIFINI TUTABIİLİYORUZ.
