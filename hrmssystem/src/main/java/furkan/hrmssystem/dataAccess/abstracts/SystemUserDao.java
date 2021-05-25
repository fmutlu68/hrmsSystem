package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.SystemUser;

public interface SystemUserDao extends JpaRepository<SystemUser, Integer>{

}
