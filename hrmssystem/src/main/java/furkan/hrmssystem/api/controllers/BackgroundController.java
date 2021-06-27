package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.adapters.abstracts.FileUploadService;
import furkan.hrmssystem.business.abstracts.BackgroundService;
import furkan.hrmssystem.business.abstracts.JobExperienceService;
import furkan.hrmssystem.business.abstracts.LanguageExperienceService;
import furkan.hrmssystem.business.abstracts.SchoolService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.ErrorResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.core.utilities.results.SuccessResult;
import furkan.hrmssystem.entities.concretes.Background;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/backgrounds")
@CrossOrigin
public class BackgroundController {
    private final BackgroundService backgroundService;
    private final JobExperienceService jobExperienceService;
    private final LanguageExperienceService languageExperienceService;
    private final SchoolService schoolService;
    private final FileUploadService fileUploadService;

    @Autowired
    public BackgroundController(BackgroundService backgroundService,
                                JobExperienceService jobExperienceService,
                                LanguageExperienceService languageExperienceService,
                                SchoolService schoolService,
                                FileUploadService fileUploadService) {
        this.backgroundService = backgroundService;
        this.jobExperienceService = jobExperienceService;
        this.languageExperienceService = languageExperienceService;
        this.schoolService = schoolService;
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/add")
    public Result addBackground(@RequestBody Background cv){
        var cvAdded = this.backgroundService.add(cv);
        cv.getSchools().forEach(school->school.setBackground(cvAdded.getData()));
        cv.getJobExperiences().forEach(experience->experience.setBackground(cvAdded.getData()));
        cv.getLanguageExperiences().forEach(language->language.setBackground(cvAdded.getData()));
        var schoolResult = this.schoolService.addAll(cv.getSchools());
        if (!schoolResult.isSuccess()){
            this.backgroundService.delete(cvAdded.getData());
            return new ErrorResult(schoolResult.getMessage());
        }
        var jobExperienceResult = this.jobExperienceService.addAll(cv.getJobExperiences());
        if (!jobExperienceResult.isSuccess()){
            this.backgroundService.delete(cvAdded.getData());
            this.schoolService.deleteAll(schoolResult.getData());
            return new ErrorResult(jobExperienceResult.getMessage());
        }
        var languageResult = this.languageExperienceService.addAll(cv.getLanguageExperiences());
        if (!languageResult.isSuccess()){
            this.backgroundService.delete(cvAdded.getData());
            this.schoolService.deleteAll(schoolResult.getData());
            this.jobExperienceService.deleteAll(jobExperienceResult.getData());
            return new ErrorResult(languageResult.getMessage());
        }
        return new SuccessResult(cvAdded.getMessage());
    }

    @PostMapping("/update")
    public Result update(@RequestBody Background background) {
        var result = this.backgroundService.getById(background.getId());
        if (result.isSuccess() && result.getData() != null){
            this.schoolService.deleteAllByCvId(background.getId());
            this.languageExperienceService.deleteAllByCvId(background.getId());
            this.jobExperienceService.deleteAllByCvId(background.getId());
            background.setJobExperiences(background.getJobExperiences().stream().map(experience -> {experience.setId(0); return experience;}).collect(Collectors.toList()));
            background.setLanguageExperiences(background.getLanguageExperiences().stream().map(experience -> {experience.setId(0); return experience;}).collect(Collectors.toList()));
            background.setSchools(background.getSchools().stream().map(school -> {school.setId(0); return school;}).collect(Collectors.toList()));
            var schools = this.schoolService.addAll(background.getSchools());
            var jobExperiences = this.jobExperienceService.addAll(background.getJobExperiences());
            var languageExperiences = this.languageExperienceService.addAll(background.getLanguageExperiences());
            background.setJobExperiences(jobExperiences.getData());
            background.setSchools(schools.getData());
            background.setLanguageExperiences(languageExperiences.getData());
            return this.backgroundService.update(background);
        }
        return result;
    }

    @GetMapping("/getall")
    public DataResult<List<Background>> getAll(){
        return this.backgroundService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public DataResult<Background> getById(@PathVariable int id){
        return this.backgroundService.getById(id);
    }

    @PostMapping("/addtest")
    public DataResult addTest(@RequestBody Background cv){
        return this.backgroundService.add(cv);
    }

    @PostMapping("/addimagetocv/{id}")
    public Result addImageToCv(@PathVariable int id, @RequestBody MultipartFile file) {
        var cv = this.backgroundService.getById(id);
        if (!cv.isSuccess()){
            return new ErrorResult(cv.getMessage());
        }

        DataResult<Map> addImageResult = this.fileUploadService.saveFile(file);
        if (addImageResult.isSuccess()){
            cv.getData().setUserPhoto(addImageResult.getData().get("url").toString());
            return this.backgroundService.update(cv.getData());
        }
        return new ErrorResult(addImageResult.getMessage());
    }

    @GetMapping("getbyuser/{id}")
    public DataResult<List<Background>> getByUser(@PathVariable("id") int userId){
        return this.backgroundService.getByUserId(userId);
    }
}
