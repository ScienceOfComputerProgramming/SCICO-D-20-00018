package com.gamaza.rest4cep.design.controller;

import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternPostDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternPutDto;
import com.gamaza.rest4cep.design.dto.eventpattern.EventPatternWithListDto;
import com.gamaza.rest4cep.design.service.EventPatternService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Event Pattern Controller
 */
@RestController
@RequestMapping(value = "/design/event_pattern")
public class EventPatternController {

    // Private variables for injection
    private final EventPatternService eventPatternService;

    /**
     * Constructor injection
     */
    public EventPatternController(EventPatternService eventPatternService) {
        this.eventPatternService = eventPatternService;
    }

    @PostMapping
    public EventPatternWithListDto insert(@Valid @RequestBody EventPatternPostDto eventPatternPostDto) {
        return eventPatternService.create(eventPatternPostDto);
    }

    @GetMapping
    public List<EventPatternDto> all() {
        return eventPatternService.readAll();
    }

    @GetMapping(value = "/readytodeploy")
    public List<EventPatternDto> allReadyToDeploy() {
        return eventPatternService.readAllByIsReadyToDeploy(true);
    }

    @GetMapping(value = "/unreadytodeploy")
    public List<EventPatternDto> allNotReadyToDeploy() {
        return eventPatternService.readAllByIsReadyToDeploy(false);
    }

    @GetMapping(value = "/deployed")
    public List<EventPatternDto> allDeployed() {
        return eventPatternService.readAllByIsDeployed(true);
    }

    @GetMapping(value = "/notdeployed")
    public List<EventPatternDto> allNotDeployed() {
        return eventPatternService.readAllByIsDeployed(false);
    }

    @GetMapping(value = "/{id}")
    public EventPatternWithListDto oneById(@PathVariable Integer id) {
        return eventPatternService.readOneById(id);
    }

    @GetMapping(value = "/name")
    public EventPatternWithListDto oneByName(@RequestParam String name) {
        return eventPatternService.readOneByName(name);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody EventPatternPutDto eventPatternPutDto) {
        eventPatternService.update(id, eventPatternPutDto);
    }

    @PutMapping(value = "/ready/{id}")
    public void readyToDeploy(@PathVariable Integer id) {
        eventPatternService.updateStatus(id, true);
    }

    @PutMapping(value = "/unready/{id}")
    public void unReadyToDeploy(@PathVariable Integer id) {
        eventPatternService.updateStatus(id, false);
    }

    @PutMapping(value = "/deploy/{id}")
    public void deploy(@PathVariable Integer id) {
        eventPatternService.updateDeployingStatus(id, true);
    }

    @PutMapping(value = "/undeploy/{id}")
    public void undeploy(@PathVariable Integer id) {
        eventPatternService.updateDeployingStatus(id, false);
    }

    @PostMapping(value = "/link")
    public void link(@RequestParam Integer eventPatternId, @RequestParam Integer eventTypeId) {
        eventPatternService.setPatternLink(eventPatternId, eventTypeId, true);
    }

    @DeleteMapping(value = "/unlink")
    public void unlink(@RequestParam Integer eventPatternId, @RequestParam Integer eventTypeId) {
        eventPatternService.setPatternLink(eventPatternId, eventTypeId, false);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        eventPatternService.delete(id);
    }

}
