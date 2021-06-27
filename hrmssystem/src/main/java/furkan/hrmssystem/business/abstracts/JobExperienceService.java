package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {
    DataResult<List<JobExperience>> getAll();
    DataResult<JobExperience> add(JobExperience experience);
    DataResult<List<JobExperience>> addAll(List<JobExperience> experiences);
    Result delete(JobExperience experience);
    Result deleteAll(List<JobExperience> experiences);
    Result deleteAllByCvId(int cvId);
    DataResult<List<JobExperience>> getAllByUserIdAndOrderingEndingYear(boolean isAscending, int userId);
}
