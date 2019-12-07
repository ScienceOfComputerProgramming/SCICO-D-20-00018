package com.gamaza.rest4cep.mongo.controller;

import com.gamaza.rest4cep.mongo.dto.EplEventPatternMongoDto;
import com.gamaza.rest4cep.mongo.service.EplEventPatternMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EPL Event Patterns (Mongo version) Controller
 */
@RestController
@RequestMapping(value = "/runtime/epl_event_patterns")
public class EplEventPatternMongoController {

    /* Private variables for injection */
    private final EplEventPatternMongoService eplEventPatternMongoService;

    /**
     * Constructor injection
     * @param eplEventPatternMongoService **eplEventPatternMongoService**
     */
    public EplEventPatternMongoController(final EplEventPatternMongoService eplEventPatternMongoService) {
        this.eplEventPatternMongoService = eplEventPatternMongoService;
    }

    @PostMapping
    public @ResponseBody EplEventPatternMongoDto insert(@RequestBody EplEventPatternMongoDto eplEventPatternMongoDto){
        return eplEventPatternMongoService.create(eplEventPatternMongoDto);
    }

    @GetMapping
    public @ResponseBody List<EplEventPatternMongoDto> all(){
        return eplEventPatternMongoService.readAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody EplEventPatternMongoDto oneById(@PathVariable("id") String id){
        return eplEventPatternMongoService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public @ResponseBody List<EplEventPatternMongoDto> last5(){
        return eplEventPatternMongoService.readLast5();
    }

    @DeleteMapping
    public void deleteAll(){
        eplEventPatternMongoService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable("id") String id){
        eplEventPatternMongoService.deleteOne(id);
    }

}
