package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.dto.EventTypeMongoPostDto;
import com.gamaza.rest4cep.mongo.service.EventTypeMongoService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Event Type Controller
 */
@RestController
@RequestMapping(value = "/runtime/event_type")
public class EventTypeMongoController {

    // Private variables for injection
    private final EventTypeMongoService eventTypeMongoService;

    /**
     * Constructor injection
     */
    public EventTypeMongoController(EventTypeMongoService eventTypeMongoService) {
        this.eventTypeMongoService = eventTypeMongoService;
    }

    @PostMapping
    public EventTypeMongoDto insert(@Valid @RequestBody EventTypeMongoPostDto eventTypePostDto) {
        return eventTypeMongoService.create(eventTypePostDto);
    }

    @GetMapping
    public List<EventTypeMongoDto> all() {
        return eventTypeMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public EventTypeMongoDto oneById(@PathVariable String id) {
        return eventTypeMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<EventTypeMongoDto> last5() {
        return eventTypeMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        eventTypeMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        eventTypeMongoService.deleteOne(id);
    }

}
