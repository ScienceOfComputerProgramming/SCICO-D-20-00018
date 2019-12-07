package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.EventTypeMongoDto;
import com.gamaza.rest4cep.mongo.service.EventTypeMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Event Types (Mongo version) Controller
 */
@RestController
@RequestMapping(value = "/runtime/event_types")
public class EventTypeMongoController {

    /* Private variables for injection */
    private final EventTypeMongoService eventTypeMongoService;

    /**
     * Constructor injection
     * @param eventTypeMongoService **eventTypeMongoService**
     */
    public EventTypeMongoController(final EventTypeMongoService eventTypeMongoService){
        this.eventTypeMongoService = eventTypeMongoService;
    }

    @PostMapping
    public @ResponseBody EventTypeMongoDto insert(@RequestBody EventTypeMongoDto eventTypeMongoDto){
        return eventTypeMongoService.create(eventTypeMongoDto);
    }

    @GetMapping
    public @ResponseBody List<EventTypeMongoDto> all(){
        return eventTypeMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody EventTypeMongoDto oneById(@PathVariable("id") String id){
        return eventTypeMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public @ResponseBody List<EventTypeMongoDto> last5(){
        return eventTypeMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll(){
        eventTypeMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable("id") String id){
        eventTypeMongoService.deleteOne(id);
    }

}
