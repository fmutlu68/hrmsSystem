package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer>{

    @Query("select s from User u Join SystemUser s On u.email=:email Where s.userId=u.userId")
    SystemUser getByEmailIsSystemUser(String email);
}
