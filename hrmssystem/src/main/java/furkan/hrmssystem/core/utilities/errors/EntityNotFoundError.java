package furkan.hrmssystem.core.utilities.errors;

import furkan.hrmssystem.core.utilities.errors.abstracts.BaseError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class EntityNotFoundError extends RuntimeException implements BaseError {
    private String entityName;
    private final LocalDateTime failedAt;
    private String operationName;
    public EntityNotFoundError(String entityName, String operationName, String message){
        super(message);
        this.failedAt = LocalDateTime.now();
        this.entityName = entityName;
        this.operationName = operationName;
    }

    @Override
    public String getErrorMessage() {
        return entityName + " - " + operationName + " --- " + super.getMessage();
    }
}
