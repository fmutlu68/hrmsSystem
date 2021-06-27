package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.FavoriteJobPostingService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.core.utilities.results.Result;
import furkan.hrmssystem.entities.concretes.FavoriteJobPosting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritejobpostings/")
@CrossOrigin
public class FavoriteJobPostingController {

    private final FavoriteJobPostingService service;

    @Autowired
    public FavoriteJobPostingController(FavoriteJobPostingService service) {
        this.service = service;
    }

    @GetMapping("/getbyuserid/{id}")
    public DataResult<List<FavoriteJobPosting>> getByUserId(@PathVariable int id) {
        return this.service.getFavoritesByUserId(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody FavoriteJobPosting favoriteJobPosting) {
        return this.service.add(favoriteJobPosting);
    }
}
