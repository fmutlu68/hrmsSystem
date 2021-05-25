package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer>{

}
