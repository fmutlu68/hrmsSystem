package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.LanguageExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageExperienceDao extends JpaRepository<LanguageExperience, Integer> {

    List<LanguageExperience> getByBackground_Id(int background_id);

}
