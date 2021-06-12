package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.entities.concretes.City;

import java.util.List;

public interface CityService {
    DataResult<List<City>> getAll();
}
