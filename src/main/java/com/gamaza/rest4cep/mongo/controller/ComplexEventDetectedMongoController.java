package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.service.ComplexEventDetectedMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Complex Events Detected Controller
 */
@RestController
@RequestMapping(value = "/runtime/complex_events_detected")
public class ComplexEventDetectedMongoController {

    /* Private variables for injection */
    private final ComplexEventDetectedMongoService complexEventDetectedMongoService;

    /**
     * Constructor injection
     * @param complexEventDetectedMongoService **complexEventDetectedMongoService**
     */
    public ComplexEventDetectedMongoController(final ComplexEventDetectedMongoService complexEventDetectedMongoService) {
        this.complexEventDetectedMongoService = complexEventDetectedMongoService;
    }

    @PostMapping
    public @ResponseBody ComplexEventDetectedMongoDto insert(@RequestBody ComplexEventDetectedMongoDto complexEventDetectedMongoDto){
        return complexEventDetectedMongoService.create(complexEventDetectedMongoDto);
    }

    @GetMapping
    public @ResponseBody List<ComplexEventDetectedMongoDto> all(){
        return complexEventDetectedMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody ComplexEventDetectedMongoDto oneById(@PathVariable("id") String id){
        return complexEventDetectedMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public @ResponseBody List<ComplexEventDetectedMongoDto> last5(){
        return complexEventDetectedMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll(){
        complexEventDetectedMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable("id") String id){
        complexEventDetectedMongoService.deleteOne(id);
    }

}
