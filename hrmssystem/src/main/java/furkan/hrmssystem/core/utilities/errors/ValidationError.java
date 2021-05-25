package furkan.hrmssystem.core.utilities.errors;

import furkan.hrmssystem.core.utilities.errors.abstracts.BaseError;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ValidationError extends RuntimeException implements BaseError {
	private LocalDateTime failedTime;
	private String fieldName;

	public ValidationError(String message, String fieldName) {
		super(message);
		this.failedTime = LocalDateTime.now();
		this.fieldName = fieldName;
	}

	@Override
	public String getErrorMessage() {
		return fieldName + " --- " + super.getMessage();
	}
}
