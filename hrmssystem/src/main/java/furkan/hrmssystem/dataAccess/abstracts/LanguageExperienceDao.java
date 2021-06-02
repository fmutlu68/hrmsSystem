package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.LanguageExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageExperienceDao extends JpaRepository<LanguageExperience, Integer> {
}
