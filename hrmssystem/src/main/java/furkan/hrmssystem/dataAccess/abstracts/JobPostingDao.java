package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
    
    @Query("select new furkan.hrmssystem.entities.dtos.JobPostingDto(id, user.companyName, jobPosition.jobPositionName, vacancy, addedDate, deadline) from JobPosting where active=true")
    List<JobPostingDto> getAllIsActive();

    List<JobPosting> getByActiveTrueAndUser_CompanyName(String user_companyName);

    List<JobPosting> getByUser_UserId(int user_userId);

    List<JobPosting> getByActivatedFalse();
}
