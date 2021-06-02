package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.Background;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackgroundDao extends JpaRepository<Background, Integer> {
    List<Background> getByUser_UserId(int userId);
}
