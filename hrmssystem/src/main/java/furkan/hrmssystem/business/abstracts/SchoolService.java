package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.School;

import java.util.List;

public interface SchoolService {
    DataResult<List<School>> getAll();
    DataResult<School> add(School school);
    DataResult<List<School>> addAll(List<School> schools);
    Result delete(School school);
    Result deleteAll(List<School> schools);
    DataResult<List<School>> getAllByUserIdAndOrderingEndingYear(boolean isAscending, int userId);
}
