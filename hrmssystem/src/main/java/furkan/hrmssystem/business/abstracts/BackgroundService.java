package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.Background;

import java.util.List;

public interface BackgroundService {
    DataResult<List<Background>> getAll();
    DataResult<Background> getById(int id);
    DataResult<List<Background>> getByUserId(int userId);
    DataResult<Background> add(Background cv);
    Result delete(Background cv);
    Result update(Background cv);
}
