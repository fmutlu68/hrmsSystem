package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.JobPostingService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.JobPostingDao;
import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private final JobPostingDao jobPostingDao;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao) {
        this.jobPostingDao = jobPostingDao;
    }

    @Override
    public DataResult<List<JobPostingDto>> getallByActiveIsTrue() {
        return new SuccessDataResult<List<JobPostingDto>>(this.jobPostingDao.getAllIsActive());
    }

    @Override
    public DataResult<List<JobPosting>> getallOrderedByDeadline(Sort.Direction direction) {
        return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(generateSort("deadline", direction)));
    }

    @Override
    public DataResult<List<JobPosting>> getallOrderedByAddedDate(Sort.Direction direction) {
        return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.findAll(generateSort("addedDate", direction)));
    }

    @Override
    public DataResult<List<JobPosting>> getAllByCompanyName(String companyName) {
        return new SuccessDataResult<List<JobPosting>>(this.jobPostingDao.getByActiveTrueAndUser_CompanyName(companyName));
    }

    @Override
    public DataResult<List<JobPosting>> getAllByEmployerId(int userId) {
        return new SuccessDataResult<>(this.jobPostingDao.getByUser_UserId(userId),"İş İlanları Listelendi.");
    }

    @Override
    public DataResult<List<JobPosting>> getAllNoActivated() {
        return new SuccessDataResult<>(this.jobPostingDao.getByActivatedFalse(), "İlanlar Listelendi.");
    }

    @Override
    public Result changeVisibility(int id, int visibility) {
        JobPosting posting = this.jobPostingDao.findById(id).orElse(null);
        if (posting == null){
            return new ErrorResult("İş İlanı Bulunamadı.");
        }else{
            posting.setActive(visibility == 1);
        }
        this.jobPostingDao.save(posting);
        return new SuccessResult("İş İlanı Güncellendi.");
    }

    @Override
    public Result activateJobPosting(int id) {
        var jobPosting = this.jobPostingDao.findById(id).orElse(null);
        if (jobPosting == null){
            return new ErrorResult("İş İlanı Bulunamadı");
        }
        jobPosting.setActivated(true);
        this.jobPostingDao.save(jobPosting);
        return new SuccessResult("İş İlanı Onaylandı.");
    }

    @Override
    public Result add(JobPosting posting) {
        this.jobPostingDao.save(posting);
        posting.setActive(false);
        return new SuccessResult("İş İlanı Eklendi!");
    }

    @Override
    public Result delete(JobPosting posting) {
        this.jobPostingDao.delete(posting);
        return new SuccessResult("İş İlanı Silindi.");
    }

    private Sort generateSort(String column, Sort.Direction direction) {return Sort.by(direction,column);}
}
