package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOperationDao extends JpaRepository<UserOperation, Integer> {
    List<UserOperation> getByOldUser_UserIdOrNewUser_UserId(int oldUser_userId, int newUser_userId);
    List<UserOperation> findByActivatedIsFalse();
}
