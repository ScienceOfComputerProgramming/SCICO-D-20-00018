package com.gamaza.rest4cep.mysql.controller;

import com.gamaza.rest4cep.mysql.dto.EventTypeWithListDto;
import com.gamaza.rest4cep.mysql.service.EventTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Event Types Controller
 */
@RestController
@RequestMapping(value = "/design/event_types")
public class EventTypeController {

    /* Private variables for injection */
    private final EventTypeService eventTypeService;

    /**
     * Constructor injection
     * @param eventTypeService **eventTypeService**
     */
    public EventTypeController(final EventTypeService eventTypeService){
        this.eventTypeService = eventTypeService;
    }

    @PostMapping
    public @ResponseBody EventTypeWithListDto insert(@RequestBody EventTypeWithListDto eventTypeWithListDto){
        return eventTypeService.create(eventTypeWithListDto);
    }

    @GetMapping
    public @ResponseBody List<EventTypeWithListDto> all(){
        return eventTypeService.readAll();
    }

    @GetMapping(value = "/enabled")
    public @ResponseBody List<EventTypeWithListDto> allEnabled(){
        return eventTypeService.readAllByIsEnabled(true);
    }

    @GetMapping(value = "/disabled")
    public @ResponseBody List<EventTypeWithListDto> allDisabled(){
        return eventTypeService.readAllByIsEnabled(false);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody EventTypeWithListDto oneById(@PathVariable("id") Integer id){
        return eventTypeService.readOneById(id);
    }

    @GetMapping(value = "/name/{name}")
    public @ResponseBody EventTypeWithListDto oneByName(@PathVariable("name") String name){
        return eventTypeService.readOneByName(name);
    }

    @GetMapping(value = "/channel/{channelId}")
    public @ResponseBody EventTypeWithListDto oneByChannelId(@PathVariable("channelId") Integer channelId){
        return eventTypeService.readOneByChannelId(channelId);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody EventTypeWithListDto eventTypeWithListDto){
        eventTypeService.update(id, eventTypeWithListDto);
    }

    @PutMapping(value = "/enable/{id}")
    public void enable(@PathVariable("id") Integer id){
        eventTypeService.updateStatus(id, true);
    }

    @PutMapping(value = "/disable/{id}")
    public void disable(@PathVariable("id") Integer id){
        eventTypeService.updateStatus(id, false);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        eventTypeService.delete(id);
    }

}
