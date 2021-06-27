package furkan.hrmssystem.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import furkan.hrmssystem.entities.concretes.User;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface UserDao extends JpaRepository<User, Integer>{
    User getByEmail(String email);

    User getByEmailAndPassword(String email, String password);
}
