package furkan.hrmssystem.dataAccess.abstracts;

import furkan.hrmssystem.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDao extends JpaRepository<City, Integer> {
}
