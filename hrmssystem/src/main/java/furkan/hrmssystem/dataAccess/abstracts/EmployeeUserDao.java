package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.EmployeeUser;

public interface EmployeeUserDao extends JpaRepository<EmployeeUser, Integer>{

}
