package furkan.hrmssystem.core.utilities.errors;

import furkan.hrmssystem.core.utilities.errors.abstracts.BaseError;
import lombok.Getter;

@Getter
public class IdentityNoAlreadyExistsError extends RuntimeException implements BaseError  {
	
	public IdentityNoAlreadyExistsError() {
		super("Girilen T.C. No İle Zaten Kayıt Olunmuş.");
	}

	@Override
	public String getErrorMessage() {
		return super.getMessage();
	}

}
