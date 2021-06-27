package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
    
    Page<List<JobPosting>> getByActiveTrue(Pageable pageable);

    Page<List<JobPosting>> getByActiveTrueAndUser_CompanyName(String user_companyName, Pageable pageable );

    Page<List<JobPosting>> getByUser_UserId(int user_userId, Pageable pageable);

    Page<List<JobPosting>> getByActivatedFalse(Pageable pageable);

    Page<List<JobPosting>> getByMaxPayLessThanEqualAndMinPayGreaterThanEqual(int maxPay, int minPay, Pageable pageable );

    Page<List<JobPosting>> getByDeadlineLessThanEqual(Date deadline, Pageable pageable );

    Page<List<JobPosting>> getByJobPosition_Id(int jobPosition_id, Pageable pageable );


}
