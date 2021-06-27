package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.OperationType;

import java.util.List;

public interface OperationTypeService {
    Result addOperationType(OperationType type);
    DataResult<List<OperationType>> getAll();
}
