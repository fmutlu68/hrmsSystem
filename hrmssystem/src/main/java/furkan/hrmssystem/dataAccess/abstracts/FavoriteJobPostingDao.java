package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.FavoriteJobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteJobPostingDao extends JpaRepository<FavoriteJobPosting, Integer> {
    List<FavoriteJobPosting> getByUserId(int userId);
}
