package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.JobExperienceService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobexperiences")
public class JobExperiencesController {

    private final JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JobExperience experience){
        var result = this.jobExperienceService.add(experience);
        if (!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getbyuserandasc/{id}")
    public DataResult<List<JobExperience>> getByUserAndAsc(@PathVariable("id") int userId){
        return this.jobExperienceService.getAllByUserIdAndOrderingEndingYear(true, userId);
    }

    @GetMapping("/getbyuseranddesc/{id}")
    public DataResult<List<JobExperience>> getByUserAndDesc(@PathVariable("id") int userId){
        return this.jobExperienceService.getAllByUserIdAndOrderingEndingYear(false, userId);
    }
}
