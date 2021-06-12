package furkan.hrmssystem.api.controllers;

import java.util.List;

import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import furkan.hrmssystem.business.abstracts.JobPositionService;
import furkan.hrmssystem.entities.concretes.JobPosition;

@RestController
@RequestMapping("/api/jobpositions")
@CrossOrigin
public class JobPositionsController {
	private JobPositionService jobPositionService;

	@Autowired
	public JobPositionsController(JobPositionService jobPositionService) {
		super();
		this.jobPositionService = jobPositionService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobPosition>> getAll(){
		return jobPositionService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobPosition jobPosition){
		return jobPositionService.add(jobPosition);
	}
}
