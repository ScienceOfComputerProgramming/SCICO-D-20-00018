package com.gamaza.rest4cep.runtime.controller;

import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypeDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventtype.IncorrectEventTypePostDto;
import com.gamaza.rest4cep.runtime.service.IncorrectEventTypeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Incorrect Event Type Controller
 */
@RestController
@RequestMapping(value = "/runtime/incorrect_event_type")
public class IncorrectEventTypeController {

    // Private variables for injection
    private final IncorrectEventTypeService incorrectEventTypeService;

    /**
     * Constructor injection
     */
    public IncorrectEventTypeController(IncorrectEventTypeService incorrectEventTypeService) {
        this.incorrectEventTypeService = incorrectEventTypeService;
    }

    @PostMapping
    public IncorrectEventTypeDto insert(@Valid @RequestBody IncorrectEventTypePostDto incorrectEventTypePostDto) {
        return incorrectEventTypeService.create(incorrectEventTypePostDto);
    }

    @GetMapping
    public List<IncorrectEventTypeDto> all() {
        return incorrectEventTypeService.readAll();
    }

    @GetMapping(value = "/{id}")
    public IncorrectEventTypeDto oneById(@PathVariable String id) {
        return incorrectEventTypeService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<IncorrectEventTypeDto> last5() {
        return incorrectEventTypeService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        incorrectEventTypeService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        incorrectEventTypeService.deleteOne(id);
    }

}
