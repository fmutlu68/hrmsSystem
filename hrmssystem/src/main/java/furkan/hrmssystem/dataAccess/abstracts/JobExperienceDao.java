package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.JobExperience;
import furkan.hrmssystem.entities.dtos.JobExperienceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobExperienceDao extends JpaRepository<JobExperience, Integer> {

//    @Query("select new furkan.hrmssystem.entities.dtos.JobExperienceDto(jb.id, jb.workplaceName, jb.jobPosition, b.user.firstName, b.user.lastName, jb.beginningYear, jb.endingYear) " +
//                    "from JobExperience jb inner join jb.background b order by jb.endingYear")
//    List<JobExperienceDto> getAllByOrderByEndingYear();

    @Query("from JobExperience where background.user.userId = :userId order by endingYear asc nulls first")
    List<JobExperience> getAllUserAndOrderedByAsc(int userId);

    @Query("from JobExperience where background.user.userId = :userId order by endingYear desc nulls first ")
    List<JobExperience> getAllUserAndOrderedByDesc(int userId);

}
