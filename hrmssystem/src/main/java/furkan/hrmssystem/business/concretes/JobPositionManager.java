package furkan.hrmssystem.business.concretes;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import furkan.hrmssystem.business.abstracts.JobPositionService;
import furkan.hrmssystem.dataAccess.abstracts.JobPositionDao;
import furkan.hrmssystem.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{
	private JobPositionDao jobPositionDao;

	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll());
	}

	@Override
	public DataResult<JobPosition> getById(int id) {
		return new SuccessDataResult<JobPosition>(jobPositionDao.findById(id).get());
	}

	@Override
	public Result add(JobPosition position) {
		jobPositionDao.save(position);
		return new SuccessResult("İş Pozisyonu Eklendi.");
	}


}
