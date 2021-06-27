package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.EmployerUser;
import org.springframework.data.jpa.repository.Query;

public interface EmployerUserDao extends JpaRepository<EmployerUser, Integer>{

    @Query("Select e From User u Join EmployerUser e On e.email=u.email Where u.email=:email and e.isOld = false")
    EmployerUser getByEmailIsEmployer(String email);

}
