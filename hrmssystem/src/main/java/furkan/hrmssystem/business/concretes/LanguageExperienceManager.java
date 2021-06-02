package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.LanguageExperienceService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import furkan.hrmssystem.dataAccess.abstracts.LanguageExperienceDao;
import furkan.hrmssystem.entities.concretes.LanguageExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LanguageExperienceManager implements LanguageExperienceService {

    private final LanguageExperienceDao dao;

    @Autowired
    public LanguageExperienceManager(LanguageExperienceDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<LanguageExperience> add(LanguageExperience experience) {
        return new SuccessDataResult<LanguageExperience>(this.dao.save(experience));
    }

    @Override
    public DataResult<List<LanguageExperience>>  addAll(List<LanguageExperience> experiences) {
        return new SuccessDataResult<>(this.dao.saveAll(experiences), "Diller Eklendi.");
    }

    @Override
    public Result delete(LanguageExperience experience) {
        this.dao.delete(experience);
        return new SuccessResult("Bilinen Dil: " + experience.getLanguageName() +" Silindi.");
    }
}
