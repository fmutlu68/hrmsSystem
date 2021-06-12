package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface JobPostingService {
    DataResult<List<JobPostingDto>> getallByActiveIsTrue();
    DataResult<List<JobPosting>> getallOrderedByDeadline(Sort.Direction direction);
    DataResult<List<JobPosting>> getallOrderedByAddedDate(Sort.Direction direction);
    DataResult<List<JobPosting>> getAllByCompanyName(String companyName);
    DataResult<List<JobPosting>> getAllByEmployerId(int userId);
    DataResult<List<JobPosting>> getAllNoActivated();
    Result changeVisibility(int id, int visibility);
    Result activateJobPosting(int id);
    Result add(JobPosting posting);
    Result delete(JobPosting posting);
}
