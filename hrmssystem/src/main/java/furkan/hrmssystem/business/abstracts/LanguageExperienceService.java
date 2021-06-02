package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.LanguageExperience;

import java.util.List;

public interface LanguageExperienceService {
    DataResult<LanguageExperience> add(LanguageExperience experience);
    DataResult<List<LanguageExperience>> addAll(List<LanguageExperience> experiences);
    Result delete(LanguageExperience experience);
}
