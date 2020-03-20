package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedMongoDto;
import com.gamaza.rest4cep.mongo.dto.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.mongo.service.ComplexEventDetectedMongoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Complex Events Detected Controller
 */
@RestController
@RequestMapping(value = "/runtime/complex_events_detected")
public class ComplexEventDetectedMongoController {

    // Private variables for injection
    private final ComplexEventDetectedMongoService complexEventDetectedMongoService;

    /**
     * Constructor injection
     */
    public ComplexEventDetectedMongoController(ComplexEventDetectedMongoService complexEventDetectedMongoService) {
        this.complexEventDetectedMongoService = complexEventDetectedMongoService;
    }

    @PostMapping
    public ComplexEventDetectedMongoDto insert(@Valid @RequestBody ComplexEventDetectedPostDto complexEventDetectedPostDto) {
        return complexEventDetectedMongoService.create(complexEventDetectedPostDto);
    }

    @GetMapping
    public List<ComplexEventDetectedMongoDto> all() {
        return complexEventDetectedMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public ComplexEventDetectedMongoDto oneById(@PathVariable String id) {
        return complexEventDetectedMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<ComplexEventDetectedMongoDto> last5() {
        return complexEventDetectedMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        complexEventDetectedMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        complexEventDetectedMongoService.deleteOne(id);
    }

}
