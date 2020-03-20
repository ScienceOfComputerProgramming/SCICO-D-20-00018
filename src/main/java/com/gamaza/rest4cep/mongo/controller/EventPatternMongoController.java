package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.EventPatternMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventPatternMongoPostDto;
import com.gamaza.rest4cep.mongo.service.EventPatternMongoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Event Pattern  Controller
 */
@RestController
@RequestMapping(value = "/runtime/event_pattern")
public class EventPatternMongoController {

    // Private variables for injection
    private final EventPatternMongoService eventPatternMongoService;

    /**
     * Constructor injection
     */
    public EventPatternMongoController(EventPatternMongoService eventPatternMongoService) {
        this.eventPatternMongoService = eventPatternMongoService;
    }

    @PostMapping
    public EventPatternMongoDto insert(@Valid @RequestBody EventPatternMongoPostDto eventPatternPostDto) {
        return eventPatternMongoService.create(eventPatternPostDto);
    }

    @GetMapping
    public List<EventPatternMongoDto> all() {
        return eventPatternMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public EventPatternMongoDto oneById(@PathVariable String id) {
        return eventPatternMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<EventPatternMongoDto> last5() {
        return eventPatternMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        eventPatternMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        eventPatternMongoService.deleteOne(id);
    }

}
