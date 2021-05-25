package furkan.hrmssystem.core.utilities.errors;

import furkan.hrmssystem.core.utilities.errors.abstracts.BaseError;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EnumNotFoundError extends RuntimeException implements BaseError {
    private String enumName;
    private final LocalDateTime failedAt;

    public EnumNotFoundError(String message, String enumName){
        super(message);
        this.enumName = enumName;
        this.failedAt = LocalDateTime.now();
    }

    @Override
    public String getErrorMessage() {
        return super.getMessage();
    }
}
