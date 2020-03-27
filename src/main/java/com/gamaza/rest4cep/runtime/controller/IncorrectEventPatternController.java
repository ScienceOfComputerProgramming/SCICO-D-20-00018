package com.gamaza.rest4cep.runtime.controller;

import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternDto;
import com.gamaza.rest4cep.runtime.dto.incorrecteventpattern.IncorrectEventPatternPostDto;
import com.gamaza.rest4cep.runtime.service.IncorrectEventPatternService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Incorrect Event Pattern Controller
 */
@RestController
@RequestMapping(value = "/runtime/incorrect_event_pattern")
public class IncorrectEventPatternController {

    // Private variables for injection
    private final IncorrectEventPatternService incorrectEventPatternService;

    /**
     * Constructor injection
     */
    public IncorrectEventPatternController(IncorrectEventPatternService incorrectEventPatternService) {
        this.incorrectEventPatternService = incorrectEventPatternService;
    }

    @PostMapping
    public IncorrectEventPatternDto insert(@Valid @RequestBody IncorrectEventPatternPostDto incorrectEventPatternPostDto) {
        return incorrectEventPatternService.create(incorrectEventPatternPostDto);
    }

    @GetMapping
    public List<IncorrectEventPatternDto> all() {
        return incorrectEventPatternService.readAll();
    }

    @GetMapping(value = "/{id}")
    public IncorrectEventPatternDto oneById(@PathVariable String id) {
        return incorrectEventPatternService.readOneById(id);
    }

    @GetMapping(value = "/last")
    public List<IncorrectEventPatternDto> last5() {
        return incorrectEventPatternService.readLast5();
    }

    @DeleteMapping
    public void deleteAll() {
        incorrectEventPatternService.deleteAll();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOne(@PathVariable String id) {
        incorrectEventPatternService.deleteOne(id);
    }

}
