package furkan.hrmssystem.business.abstracts;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.JobPosition;

public interface JobPositionService {
	DataResult<List<JobPosition>> getAll();
	DataResult<JobPosition> getById(int id);
	Result add(JobPosition position);
}
