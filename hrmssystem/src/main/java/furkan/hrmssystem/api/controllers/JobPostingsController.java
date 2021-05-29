package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.JobPostingService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessDataResult;
import furkan.hrmssystem.entities.concretes.JobPosting;
import furkan.hrmssystem.entities.dtos.JobPostingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postings")
public class JobPostingsController {
    private final JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getallbyactive")
    public DataResult<List<JobPostingDto>> getAllByActiveIs(){
        return this.jobPostingService.getallByActiveIsTrue();
    }

    @GetMapping("/getallorderedbydeadline/{order}")
    public DataResult<List<JobPosting>> getAllByOrderedByDeadline(@PathVariable("order") String order){
        if (order.toLowerCase().equals("asc")){
            return this.jobPostingService.getallOrderedByDeadline(Sort.Direction.ASC);
        }
        return this.jobPostingService.getallOrderedByDeadline(Sort.Direction.DESC);
    }

    @GetMapping("/getallorderedbyaddeddate/{order}")
    public DataResult<List<JobPosting>> getAllByOrderedByAddedDate(@PathVariable("order") String order){
        if (order.toLowerCase().equals("asc")){
            return this.jobPostingService.getallOrderedByAddedDate(Sort.Direction.ASC);
        }
        return this.jobPostingService.getallOrderedByAddedDate(Sort.Direction.DESC);
    }

    @GetMapping("/getbycompany/{companyname}")
    public DataResult<List<JobPosting>> getByCompanyName(@PathVariable("companyname") String companyName){
        return this.jobPostingService.getAllByCompanyName(companyName);
    }

    @GetMapping("/changevisibility/{id}/{visibility}")
    public Result changeVisibility(@PathVariable("id") int id, @PathVariable("visibility") int visibility){
        return this.jobPostingService.changeVisibility(id, visibility);
    }
}
