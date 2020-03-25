package com.gamaza.rest4cep.runtime.controller;

import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedDto;
import com.gamaza.rest4cep.runtime.dto.complexeventdetected.ComplexEventDetectedPostDto;
import com.gamaza.rest4cep.runtime.service.ComplexEventDetectedService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Complex Events Detected Controller
 */
@RestController
@RequestMapping(value = "/runtime/complex_events_detected")
public class ComplexEventDetectedController {

    // Private variables for injection
    private final ComplexEventDetectedService complexEventDetectedService;

    /**
     * Constructor injection
     */
    public ComplexEventDetectedController(ComplexEventDetectedService complexEventDetectedService) {
        this.complexEventDetectedService = complexEventDetectedService;
    }

    @PostMapping
    public ComplexEventDetectedDto insert(@Valid @RequestBody ComplexEventDetectedPostDto complexEventDetectedPostDto) {
        return complexEventDetectedService.create(complexEventDetectedPostDto);
    }

    @GetMapping
    public List<ComplexEventDetectedDto> all() {
        return complexEventDetectedService.readAll();
    }

    @GetMapping(value = "/{id}")
    public ComplexEventDetectedDto oneById(@PathVariable String id) {
        return complexEventDetectedService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<ComplexEventDetectedDto> last5() {
        return complexEventDetectedService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        complexEventDetectedService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        complexEventDetectedService.deleteOne(id);
    }

}
