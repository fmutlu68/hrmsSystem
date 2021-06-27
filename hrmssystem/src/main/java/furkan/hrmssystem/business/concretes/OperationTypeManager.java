package furkan.hrmssystem.business.concretes;

import furkan.hrmssystem.business.abstracts.OperationTypeService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import furkan.hrmssystem.dataAccess.abstracts.OperationTypeDao;
import furkan.hrmssystem.entities.concretes.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationTypeManager implements OperationTypeService {

    private final OperationTypeDao dao;

    @Autowired
    public OperationTypeManager(OperationTypeDao dao) {
        this.dao = dao;
    }

    @Override
    public Result addOperationType(OperationType type) {
        this.dao.save(type);
        return new SuccessResult("Operasyon Tipi Ekleme Başarılı!");
    }

    @Override
    public DataResult<List<OperationType>> getAll() {
        return new SuccessDataResult<List<OperationType>>(this.dao.findAll(), "Operasyon Tipleri Listelendi.");
    }
}
