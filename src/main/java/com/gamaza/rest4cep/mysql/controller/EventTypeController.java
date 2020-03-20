package com.gamaza.rest4cep.mysql.controller;

import com.gamaza.rest4cep.mysql.dto.EventTypeDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePostDto;
import com.gamaza.rest4cep.mysql.dto.EventTypePutDto;
import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;
import com.gamaza.rest4cep.mysql.service.EventTypeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Event Type Controller
 */
@RestController
@RequestMapping(value = "/design/event_type")
public class EventTypeController {

    // Private variables for injection
    private final EventTypeService eventTypeService;

    /**
     * Constructor injection
     */
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @PostMapping
    public EventTypeWithListDto insert(@Valid @RequestBody EventTypePostDto eventTypePostDto) {
        return eventTypeService.create(eventTypePostDto);
    }

    @GetMapping
    public List<EventTypeDto> all() {
        return eventTypeService.readAll();
    }

    @GetMapping(value = "/enabled")
    public List<EventTypeDto> allEnabled() {
        return eventTypeService.readAllByIsEnabled(true);
    }

    @GetMapping(value = "/disabled")
    public List<EventTypeDto> allDisabled() {
        return eventTypeService.readAllByIsEnabled(false);
    }

    @GetMapping(value = "/{id}")
    public EventTypeWithListDto oneById(@PathVariable Integer id) {
        return eventTypeService.readOneById(id);
    }

    @GetMapping(value = "/name")
    public EventTypeWithListDto oneByName(@RequestParam String name) {
        return eventTypeService.readOneByName(name);
    }

    @GetMapping(value = "/channel/{channelId}")
    public EventTypeWithListDto oneByChannelId(@PathVariable Integer channelId) {
        return eventTypeService.readOneByChannelId(channelId);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody EventTypePutDto eventTypePutDto) {
        eventTypeService.update(id, eventTypePutDto);
    }

    @PutMapping(value = "/enable/{id}")
    public void enable(@PathVariable Integer id) {
        eventTypeService.updateStatus(id, true);
    }

    @PutMapping(value = "/disable/{id}")
    public void disable(@PathVariable Integer id) {
        eventTypeService.updateStatus(id, false);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        eventTypeService.delete(id);
    }

}
