package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.CityService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.dataAccess.abstracts.CityDao;
import furkan.hrmssystem.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {

    private CityDao dao;

    @Autowired
    public CityManager(CityDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.dao.findAll());
    }
}
