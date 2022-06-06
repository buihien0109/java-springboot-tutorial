package com.example.pathvariable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("validation")
@Slf4j
@Validated
public class ValidationController {
    @GetMapping("/users/{id}")
    public void getUser(@PathVariable @Min(1) @Max(7) int id) {
        log.info("User id = " + id);
    }
}
