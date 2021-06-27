package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationTypeDao extends JpaRepository<OperationType, Integer> {

}
