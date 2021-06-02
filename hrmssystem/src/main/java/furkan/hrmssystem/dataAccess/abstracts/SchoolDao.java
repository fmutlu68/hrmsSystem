package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolDao extends JpaRepository<School, Integer> {

    @Query("from School where background.user.userId = :userId order by graduationYear asc nulls first ")
    List<School> getAllUserAndOrderedByAsc(int userId);

    @Query("from School where background.user.userId = :userId order by graduationYear desc nulls first ")
    List<School> getAllUserAndOrderedByDesc(int userId);
}
