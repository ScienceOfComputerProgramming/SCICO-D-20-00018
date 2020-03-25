package com.gamaza.rest4cep.runtime.controller;

import com.gamaza.rest4cep.runtime.dto.event.EventDto;
import com.gamaza.rest4cep.runtime.dto.event.EventPostDto;
import com.gamaza.rest4cep.runtime.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Event Controller
 */
@RestController
@RequestMapping(value = "/runtime/event")
public class EventController {

    // Private variables for injection
    private final EventService eventService;

    /**
     * Constructor injection
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventDto insert(@Valid @RequestBody EventPostDto eventPostDto) {
        return eventService.create(eventPostDto);
    }

    @GetMapping
    public List<EventDto> all() {
        return eventService.readAll();
    }

    @GetMapping(value = "/{id}")
    public EventDto oneById(@PathVariable String id) {
        return eventService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<EventDto> last5() {
        return eventService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        eventService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        eventService.deleteOne(id);
    }

}
