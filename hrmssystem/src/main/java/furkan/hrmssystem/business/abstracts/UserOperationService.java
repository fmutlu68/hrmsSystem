package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.UserOperation;

import java.util.List;

public interface UserOperationService {
    DataResult<UserOperation> activateOperation(int operationId);
    DataResult<UserOperation> deactivateOperation(int operationId);
    DataResult<List<UserOperation>> getOperations();
    DataResult<List<UserOperation>> getOperationsNoActivated();
    Result addOperation(UserOperation operation);
    Result isActivated(int userId);
}
