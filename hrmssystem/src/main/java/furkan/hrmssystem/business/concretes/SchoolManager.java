package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.SchoolService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.SchoolDao;
import furkan.hrmssystem.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {

    private final SchoolDao dao;

    @Autowired
    public SchoolManager(SchoolDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<List<School>>(this.dao.findAll(), "Okullar Listelendi.");
    }

    @Override
    public DataResult<School> add(School school) {
        return new SuccessDataResult<School>(this.dao.save(school));
    }

    @Override
    public DataResult<List<School>> addAll(List<School> schools) {

        return new SuccessDataResult<>(this.dao.saveAll(schools), "Okullar Eklendi.");
    }

    @Override
    public Result delete(School school) {
        this.dao.delete(school);
        return new SuccessResult("Okul Silindi.");
    }

    @Override
    public Result deleteAll(List<School> schools) {
        this.dao.deleteAll(schools);
        return new SuccessResult("Okullar Silindi.");
    }

    @Override
    public Result deleteAllByCvId(int cvId) {
        var cvs = this.dao.getByBackground_Id(cvId);
        this.dao.deleteAll(cvs);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<School>> getAllByUserIdAndOrderingEndingYear(boolean isAscending, int userId) {
        if (isAscending){
            return new SuccessDataResult<List<School>>(this.dao.getAllUserAndOrderedByAsc(userId));
        }
        return new SuccessDataResult<List<School>>(this.dao.getAllUserAndOrderedByDesc(userId));
    }
}
