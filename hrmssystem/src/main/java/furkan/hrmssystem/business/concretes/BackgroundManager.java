package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.BackgroundService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.BackgroundDao;
import furkan.hrmssystem.entities.concretes.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackgroundManager implements BackgroundService {

    private final BackgroundDao dao;

    @Autowired
    public BackgroundManager(BackgroundDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<List<Background>> getAll() {
        var result = this.dao.findAll();
        return new SuccessDataResult<List<Background>>(result, "Özgeçmişler Listelendi.");
    }

    @Override
    public DataResult<Background> getById(int id) {
        var cv = this.dao.findById(id).orElse(null);
        if (cv == null){
            return new ErrorDataResult<Background>("Girilen Id'ye Ait Özgeçmiş Bulunamadı.");
        }
        return new SuccessDataResult<Background>(cv, "Özgeçmiş Bulundu.");
    }

    @Override
    public DataResult<List<Background>> getByUserId(int userId) {
        return new SuccessDataResult<List<Background>>(this.dao.getByUser_UserId(userId), "Özgeçmişler Listelendi.");
    }

    @Override
    public DataResult<Background> add(Background cv) {
        return new SuccessDataResult<Background>(this.dao.save(cv),"Özgeçmiş Eklendi.");
    }

    @Override
    public Result delete(Background cv) {
        this.dao.delete(cv);
        return new SuccessResult("Özgeçmiş Eklendi.");
    }

    @Override
    public Result update(Background cv) {
        this.dao.save(cv);
        return new SuccessResult("Öz Geçmiş Güncellendi.");
    }
}
