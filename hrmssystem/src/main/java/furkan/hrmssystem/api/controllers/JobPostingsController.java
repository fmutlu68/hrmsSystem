package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.JobPostingService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorDataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.JobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/postings")
@CrossOrigin
public class JobPostingsController {
    private final JobPostingService jobPostingService;

    @Autowired
    public JobPostingsController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping("/getallbyactive/{pageno}/{pagesize}")
    public DataResult<Page> getAllByActiveIs(@PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getallByActiveIsTrue(pageno, pagesize);
    }

    @GetMapping("/getallorderedbydeadline/{order}/{pageno}/{pagesize}")
    public DataResult<Page> getAllByOrderedByDeadline(@PathVariable("order") String order, @PathVariable int pageno, @PathVariable int pagesize){
        if (order.toLowerCase().equals("asc")){
            return this.jobPostingService.getallOrderedByDeadline(Sort.Direction.ASC, pageno, pagesize);
        }
        return this.jobPostingService.getallOrderedByDeadline(Sort.Direction.DESC, pageno, pagesize);
    }

    @GetMapping("/getallorderedbyaddeddate/{order}/{pageno}/{pagesize}")
    public DataResult<Page> getAllByOrderedByAddedDate(@PathVariable("order") String order, @PathVariable int pageno, @PathVariable int pagesize){
        if (order.toLowerCase().equals("asc")){
            return this.jobPostingService.getallOrderedByAddedDate(Sort.Direction.ASC, pageno, pagesize);
        }
        return this.jobPostingService.getallOrderedByAddedDate(Sort.Direction.DESC, pageno, pagesize);
    }

    @GetMapping("/getbycompany/{companyname}/{pageno}/{pagesize}")
    public DataResult<Page> getByCompanyName(@PathVariable("companyname") String companyName, @PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getAllByCompanyName(companyName, pageno, pagesize);
    }

    @GetMapping("/getbyemployerid/{id}/{pageno}/{pagesize}")
    public DataResult<Page> getByEmployerId(@PathVariable("id") int employerId, @PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getAllByEmployerId(employerId, pageno, pagesize);
    }

    @GetMapping("/getbymaxandminpay/{max}/{min}/{pageno}/{pagesize}")
    public DataResult<Page> getByMaxAndMinPay(@PathVariable("max") int maxPay, @PathVariable("min") int minPay, @PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getByMaxPayLessThanEqualAndMinPayGreaterThanEqual(maxPay, minPay, pageno, pagesize);
    }

    @PostMapping("/getbydeadline/{date}/{pageno}/{pagesize}")
    public DataResult<Page> getByDeadline(@PathVariable String date, @PathVariable int pageno, @PathVariable int pagesize){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date deadline;
        try {
            deadline = formatter.parse(date);
        }catch(Exception err) {
            return new ErrorDataResult<>("Tarih Uygun Değil");
        }
        return this.jobPostingService.getByDeadlineBefore(deadline, pageno, pagesize);
    }

    @GetMapping("/getbyjobpositionid/{id}/{pageno}/{pagesize}")
    public DataResult<Page> getByJobPositionId(@PathVariable("id") int jobPositionId, @PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getByJobPosition_Id(jobPositionId, pageno, pagesize);
    }

    @GetMapping("/getallnoactivated/{pageno}/{pagesize}")
    public DataResult<Page> getAllNoActivated(@PathVariable int pageno, @PathVariable int pagesize){
        return this.jobPostingService.getAllNoActivated(pageno, pagesize);
    }

    @GetMapping("/changevisibility/{id}/{visibility}")
    public Result changeVisibility(@PathVariable("id") int id, @PathVariable("visibility") int visibility){
        return this.jobPostingService.changeVisibility(id, visibility);
    }

    @GetMapping("/activatejobposting/{id}")
    public Result activateJobPosting(@PathVariable("id") int id){
        return this.jobPostingService.activateJobPosting(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobPosting posting){
        return this.jobPostingService.add(posting);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody JobPosting posting){
        return this.jobPostingService.delete(posting);
    }
}
