package com.gamaza.rest4cep.design.controller;

import com.gamaza.rest4cep.design.dto.eventtype.EventTypeDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePostDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypePutDto;
import com.gamaza.rest4cep.design.dto.eventtype.EventTypeWithListDto;
import com.gamaza.rest4cep.design.service.EventTypeService;
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

    @GetMapping(value = "/readytodeploy")
    public List<EventTypeDto> allReadyToDeploy() {
        return eventTypeService.readAllByIsReadyToDeploy(true);
    }

    @GetMapping(value = "/unreadytodeploy")
    public List<EventTypeDto> allNotReadyToDeploy() {
        return eventTypeService.readAllByIsReadyToDeploy(false);
    }

    @GetMapping(value = "/deployed")
    public List<EventTypeDto> allDeployed() {
        return eventTypeService.readAllByIsDeployed(true);
    }

    @GetMapping(value = "/notdeployed")
    public List<EventTypeDto> allNotDeployed() {
        return eventTypeService.readAllByIsDeployed(false);
    }

    @GetMapping(value = "/{id}")
    public EventTypeWithListDto oneById(@PathVariable Integer id) {
        return eventTypeService.readOneById(id);
    }

    @GetMapping(value = "/name")
    public EventTypeWithListDto oneByName(@RequestParam String name) {
        return eventTypeService.readOneByName(name);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody EventTypePutDto eventTypePutDto) {
        eventTypeService.update(id, eventTypePutDto);
    }

    @PutMapping(value = "/ready/{id}")
    public void readyToDeploy(@PathVariable Integer id) {
        eventTypeService.updateStatus(id, true);
    }

    @PutMapping(value = "/unready/{id}")
    public void unReadyToDeploy(@PathVariable Integer id) {
        eventTypeService.updateStatus(id, false);
    }

    @PutMapping(value = "/deploy/{id}")
    public void deploy(@PathVariable Integer id) {
        eventTypeService.updateDeployingStatus(id, true);
    }

    @PutMapping(value = "/undeploy/{id}")
    public void undeploy(@PathVariable Integer id) {
        eventTypeService.updateDeployingStatus(id, false);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        eventTypeService.delete(id);
    }

}
