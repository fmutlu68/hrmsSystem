package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.SchoolService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolsController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody School school){
        var result = this.schoolService.add(school);
        if (!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getbyuserandasc/{id}")
    public DataResult<List<School>> getByUserAndAsc(@PathVariable("id") int userId){
        return this.schoolService.getAllByUserIdAndOrderingEndingYear(true, userId);
    }

    @GetMapping("/getbyuseranddesc/{id}")
    public DataResult<List<School>> getByUserAndDesc(@PathVariable("id") int userId){
        return this.schoolService.getAllByUserIdAndOrderingEndingYear(false, userId);
    }
}
