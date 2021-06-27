package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.UserOperationService;
import furkan.hrmssystem.core.utilities.results.*;
import furkan.hrmssystem.dataAccess.abstracts.UserOperationDao;
import furkan.hrmssystem.entities.concretes.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOperationManager implements UserOperationService {

    private final UserOperationDao dao;

    @Autowired
    public UserOperationManager(UserOperationDao dao) {
        this.dao = dao;
    }

    @Override
    public DataResult<UserOperation> activateOperation(int operationId) {
        var operationSearch = this.dao.findById(operationId);
        if (operationSearch.isPresent()){
            var operation = operationSearch.get();
            operation.setActivated(true);
            this.dao.save(operation);
            return new SuccessDataResult<UserOperation>(operation, "İşlem Onaylandı!");
        }else {
            return new ErrorDataResult<UserOperation>("İşlem Bulunamadı.");
        }
    }

    @Override
    public DataResult<UserOperation> deactivateOperation(int operationId) {
        var operationSearch = this.dao.findById(operationId);
        if (operationSearch.isPresent()){
            this.dao.delete(operationSearch.get());
            return new SuccessDataResult<UserOperation>(operationSearch.get(), "İşlem Kaldırıldı!");
        }else {
            return new ErrorDataResult<UserOperation>("İşlem Bulunamadı!");
        }
    }

    @Override
    public DataResult<List<UserOperation>> getOperations() {
        return new SuccessDataResult<List<UserOperation>>(this.dao.findAll(), "Tüm İşlemler Listelendi.");
    }

    @Override
    public DataResult<List<UserOperation>> getOperationsNoActivated() {
        return new SuccessDataResult<List<UserOperation>>(this.dao.findByActivatedIsFalse(), "İşlemler Listelendi.");
    }

    @Override
    public Result addOperation(UserOperation operation) {
        this.dao.save(operation);
        return new SuccessResult("İşlem Başarılı");
    }

    @Override
    public Result isActivated(int userId) {
        var result = this.dao.getByOldUser_UserIdOrNewUser_UserId(userId, userId);
        if (result.size() > 0 && result.get(result.size() - 1).isActivated()){
            return new SuccessResult("Son Güncellemeniz Onaylandı!");
        }else {
            return new ErrorResult("Son Yaptığınız Güncelleme Henüz Onaylanmadı!");
        }
    }
}
