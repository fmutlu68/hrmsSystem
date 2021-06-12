package furkan.hrmssystem.api.controllers;

import furkan.hrmssystem.business.abstracts.CityService;
import furkan.hrmssystem.core.utilities.results.DataResult;
import furkan.hrmssystem.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin
public class CitiesController {

    private CityService service;

    @Autowired
    public CitiesController(CityService service){
        this.service = service;
    }

    @GetMapping("/getall")
    public DataResult<List<City>> getAll(){
        return this.service.getAll();
    }
}
