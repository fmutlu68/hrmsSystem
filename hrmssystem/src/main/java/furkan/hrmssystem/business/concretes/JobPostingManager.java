package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.JobPostingService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.JobPostingDao;
import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobPostingManager implements JobPostingService {

    private final JobPostingDao jobPostingDao;

    @Autowired
    public JobPostingManager(JobPostingDao jobPostingDao) {
        this.jobPostingDao = jobPostingDao;
    }

    @Override
    public DataResult<Page> getallByActiveIsTrue(int pageNo, int pageSize) {
        return new SuccessDataResult<Page>(this.jobPostingDao.getByActiveTrue(PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public DataResult<Page> getallOrderedByDeadline(Sort.Direction direction, int pageNo, int pageSize) {
        return new SuccessDataResult<Page>(createPage(this.jobPostingDao.findAll(generateSort("deadline", direction)), pageNo, pageSize));
    }

    @Override
    public DataResult<Page> getallOrderedByAddedDate(Sort.Direction direction, int pageNo, int pageSize) {
        return new SuccessDataResult<Page>(createPage(this.jobPostingDao.findAll(generateSort("addedDate", direction)), pageNo, pageSize));
    }

    @Override
    public DataResult<Page> getAllByCompanyName(String companyName, int pageNo, int pageSize) {
        return new SuccessDataResult<Page>(this.jobPostingDao.getByActiveTrueAndUser_CompanyName(companyName, PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public DataResult<Page> getAllByEmployerId(int userId, int pageNo, int pageSize) {
        return new SuccessDataResult<Page>(this.jobPostingDao.getByUser_UserId(userId, PageRequest.of(pageNo, pageSize)),"İş İlanları Listelendi.");
    }

    @Override
    public DataResult<Page> getAllNoActivated(int pageNo, int pageSize) {
        return new SuccessDataResult<>(this.jobPostingDao.getByActivatedFalse(PageRequest.of(pageNo, pageSize)), "İlanlar Listelendi.");
    }

    @Override
    public DataResult<Page> getByMaxPayLessThanEqualAndMinPayGreaterThanEqual(int maxPay, int minPay, int pageNo, int pageSize) {
        return new SuccessDataResult<>(this.jobPostingDao.getByMaxPayLessThanEqualAndMinPayGreaterThanEqual(maxPay, minPay, PageRequest.of(pageNo, pageSize)), "İlanlar Listelendi.");
    }

    @Override
    public DataResult<Page> getByDeadlineBefore(Date deadline, int pageNo, int pageSize) {
        return new SuccessDataResult<>(this.jobPostingDao.getByDeadlineLessThanEqual(deadline, PageRequest.of(pageNo, pageSize)), "İlanlar Listelendi.");
    }

    @Override
    public DataResult<Page> getByJobPosition_Id(int jobPositionId, int pageNo, int pageSize) {
        return new SuccessDataResult<>(this.jobPostingDao.getByJobPosition_Id(jobPositionId, PageRequest.of(pageNo, pageSize)), "İlanlar Listelendi.");
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

    private Page createPage(List list, int pageNo, int pageSize) {
        return new PageImpl(list, PageRequest.of(pageNo -1, pageSize), pageSize);
    }
}
