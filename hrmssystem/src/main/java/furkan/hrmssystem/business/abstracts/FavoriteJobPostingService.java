package furkan.hrmssystem.business.abstracts;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.FavoriteJobPosting;

import java.util.List;

public interface FavoriteJobPostingService {
    Result add(FavoriteJobPosting posting);
    DataResult<List<FavoriteJobPosting>> getFavoritesByUserId(int userId);
}
