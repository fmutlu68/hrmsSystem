package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.JobExperience;
import furkan.hrmssystem.entities.dtos.JobExperienceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

    @Query("from JobExperience where background.user.userId = :userId order by endingYear asc nulls first")
    List<JobExperience> getAllUserAndOrderedByAsc(int userId);

    @Query("from JobExperience where background.user.userId = :userId order by endingYear desc nulls first ")
    List<JobExperience> getAllUserAndOrderedByDesc(int userId);

    List<JobExperience> getByBackground_Id(int background_id);
}
