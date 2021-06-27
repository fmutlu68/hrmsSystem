package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

public interface JobPostingService {
    DataResult<Page> getallByActiveIsTrue(int pageNo, int pageSize);
    DataResult<Page> getallOrderedByDeadline(Sort.Direction direction, int pageNo, int pageSize);
    DataResult<Page> getallOrderedByAddedDate(Sort.Direction direction, int pageNo, int pageSize);
    DataResult<Page> getAllByCompanyName(String companyName, int pageNo, int pageSize);
    DataResult<Page> getAllByEmployerId(int userId, int pageNo, int pageSize);
    DataResult<Page> getAllNoActivated(int pageNo, int pageSize);
    DataResult<Page> getByMaxPayLessThanEqualAndMinPayGreaterThanEqual(int maxPay, int minPay, int pageNo, int pageSize);
    DataResult<Page> getByDeadlineBefore(Date deadline, int pageNo, int pageSize);
    DataResult<Page> getByJobPosition_Id(int jobPositionId, int pageNo, int pageSize);
    Result changeVisibility(int id, int visibility);
    Result activateJobPosting(int id);
    Result add(JobPosting posting);
    Result delete(JobPosting posting);
}
