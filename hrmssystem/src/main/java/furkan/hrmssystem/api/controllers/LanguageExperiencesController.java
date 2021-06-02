package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.LanguageExperienceService;
import furkan.hrmssystem.entities.concretes.LanguageExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/languages")
public class LanguageExperiencesController {
    private final LanguageExperienceService languageExperienceService;

    @Autowired
    public LanguageExperiencesController(LanguageExperienceService languageExperienceService) {
        this.languageExperienceService = languageExperienceService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(null);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody LanguageExperience experience){
        var result = this.languageExperienceService.add(experience);
        if (!result.isSuccess()){
            return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }
}
