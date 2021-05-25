package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.EmployerUser;

public interface EmployerUserDao extends JpaRepository<EmployerUser, Integer>{

}
