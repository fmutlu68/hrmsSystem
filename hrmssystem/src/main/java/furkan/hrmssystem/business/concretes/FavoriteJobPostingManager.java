package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.FavoriteJobPostingService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import furkan.hrmssystem.dataAccess.abstracts.FavoriteJobPostingDao;
import furkan.hrmssystem.entities.concretes.FavoriteJobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoriteJobPostingManager implements FavoriteJobPostingService {

    private final FavoriteJobPostingDao dao;

    @Autowired
    public FavoriteJobPostingManager(FavoriteJobPostingDao dao) {
        this.dao = dao;
    }

    @Override
    public Result add(FavoriteJobPosting posting) {
        posting.setAddedDate(new Date(System.currentTimeMillis()));
        this.dao.save(posting);
        return new SuccessResult("İlan Favorilere Eklendi!");
    }

    @Override
    public DataResult<List<FavoriteJobPosting>> getFavoritesByUserId(int userId) {
        return new SuccessDataResult<List<FavoriteJobPosting>>(this.dao.getByUserId(userId), "Favoriler Listelendi!");
    }
}
