package com.gamaza.rest4cep.mysql.controller;

import com.gamaza.rest4cep.mysql.dto.EplEventPatternWithListDto;
import com.gamaza.rest4cep.mysql.service.EplEventPatternService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EPL Event Patterns Controller
 */
@RestController
@RequestMapping(value = "/design/epl_event_patterns")
public class EplEventPatternController {

    /* Private variables for injection */
    private final EplEventPatternService eplEventPatternService;

    /**
     * Constructor injection
     * @param eplEventPatternService **eplEventPatternService**
     */
    public EplEventPatternController(final EplEventPatternService eplEventPatternService){
        this.eplEventPatternService = eplEventPatternService;
    }

    @PostMapping
    public @ResponseBody EplEventPatternWithListDto insert(@RequestBody EplEventPatternWithListDto eplEventPatternWithListDto){
        return eplEventPatternService.create(eplEventPatternWithListDto);
    }

    @GetMapping
    public @ResponseBody List<EplEventPatternWithListDto> all(){
        return eplEventPatternService.readAll();
    }

    @GetMapping(value = "/deployed")
    public @ResponseBody List<EplEventPatternWithListDto> allDeployed(){
        return eplEventPatternService.readAllByIsDeployed(true);
    }

    @GetMapping(value = "/undeployed")
    public @ResponseBody List<EplEventPatternWithListDto> allUndeployed(){
        return eplEventPatternService.readAllByIsDeployed(false);
    }

    @GetMapping(value = "/in_esper")
    public @ResponseBody List<EplEventPatternWithListDto> allInEsper(){
        return eplEventPatternService.readAllByIsInEsper(true);
    }

    @GetMapping(value = "/not_in_esper")
    public @ResponseBody List<EplEventPatternWithListDto> allNotInEsper(){
        return eplEventPatternService.readAllByIsInEsper(false);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody EplEventPatternWithListDto oneById(@PathVariable("id") Integer id){
        return eplEventPatternService.readOneById(id);
    }

    @GetMapping(value = "/name/{name}")
    public @ResponseBody EplEventPatternWithListDto oneByName(@PathVariable("name") String name){
        return eplEventPatternService.readOneByName(name);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody EplEventPatternWithListDto eplEventPatternWithListDto){
        eplEventPatternService.update(id, eplEventPatternWithListDto);
    }

    @PutMapping(value = "/deploy/{id}")
    public void deploy(@PathVariable("id") Integer id){
        eplEventPatternService.updateStatus(id, true);
    }

    @PutMapping(value = "/undeploy/{id}")
    public void undeploy(@PathVariable("id") Integer id){
        eplEventPatternService.updateStatus(id, false);
    }

    @PostMapping(value = "/link")
    public void link(@RequestParam("eplEventPatternId") Integer eplEventPatternId, @RequestParam("eventTypeId") Integer eventTypeId){
        eplEventPatternService.setPatternLink(eplEventPatternId, eventTypeId, true);
    }

    @PostMapping(value = "/unlink")
    public void unlink(@RequestParam("eplEventPatternId") Integer eplEventPatternId, @RequestParam("eventTypeId") Integer eventTypeId){
        eplEventPatternService.setPatternLink(eplEventPatternId, eventTypeId, false);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        eplEventPatternService.delete(id);
    }

}
