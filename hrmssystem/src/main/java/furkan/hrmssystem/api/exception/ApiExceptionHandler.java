package furkan.hrmssystem.api.exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import furkan.hrmssystem.core.utilities.errors.IdentityNoAlreadyExistsError;
import furkan.hrmssystem.core.utilities.results.ErrorDataResult;
import furkan.hrmssystem.core.utilities.results.ErrorResult;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Component
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ErrorDataResult<Object>(validationErrors, "Doğrulama Hatası Oluştu.");
	}

	@ExceptionHandler(IdentityNoAlreadyExistsError.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResult handleOtherExceptions(IdentityNoAlreadyExistsError exception) {
		return new ErrorResult(exception.getErrorMessage());
	}
}
