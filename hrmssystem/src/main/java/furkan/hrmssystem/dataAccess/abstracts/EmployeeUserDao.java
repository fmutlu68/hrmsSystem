package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.EmployeeUser;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeUserDao extends JpaRepository<EmployeeUser, Integer>{

    @Query("Select e From User u Join EmployeeUser e On e.email=:email Where u.email=:email and e.userId=u.userId")
    EmployeeUser getByEmailIsEmployee(String email);
}
