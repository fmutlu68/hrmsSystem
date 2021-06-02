package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.JobExperienceService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.JobExperienceDao;
import furkan.hrmssystem.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {

    private final JobExperienceDao dao;

    @Autowired
    public JobExperienceManager(JobExperienceDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<>(this.dao.findAll(), "İş Tecrübeleri Listelendi.");
    }

    @Override
    public DataResult<JobExperience> add(JobExperience experience) {
        return new SuccessDataResult<JobExperience>(this.dao.save(experience), "İş Tecrübesi Eklendi.");
    }

    @Override
    public DataResult<List<JobExperience>>  addAll(List<JobExperience> experiences) {
        return new SuccessDataResult<>(this.dao.saveAll(experiences), "İş Tecrübeleri Eklendi.");
    }

    @Override
    public Result delete(JobExperience experience) {
        this.dao.delete(experience);
        return new SuccessResult("İş Tecrübesi Silindi.");
    }

    @Override
    public Result deleteAll(List<JobExperience> experiences) {
        this.dao.deleteAll(experiences);
        return new SuccessResult("İş Tecrübeleri Silindi.");
    }

    @Override
    public DataResult<List<JobExperience>> getAllByUserIdAndOrderingEndingYear(boolean isAscending, int userId) {
        if (isAscending){
            return new SuccessDataResult<List<JobExperience>>(this.dao.getAllUserAndOrderedByAsc(userId));
        }
        return new SuccessDataResult<List<JobExperience>>(this.dao.getAllUserAndOrderedByDesc(userId));
    }
}
