package com.example.pathvariable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class RequestParamController {

    @GetMapping("/api/foos")
    public void getFoos(@RequestParam String id) {
        log.info("id = " + id);
    }

    @GetMapping("/api/bars")
    public void getBars(@RequestParam(name = "id") String barId) {
        // Nếu không có id  -> error
        log.info("id = " + barId);
    }

    @GetMapping("/api/bars-other")
    public void getBarsOther(@RequestParam(name = "id", required = false) String barId) {
        // Nếu không có id  -> null
        log.info("id = " + barId);
    }

    @GetMapping("/api/users")
    public void getBars(@RequestParam(required = false, defaultValue = "1") Optional<String> id) {
        if(id.isPresent()) {
            log.info("id = " + id.get());
            return;
        }

        // Nếu không có id -> lấy giá trị default
        log.info("Không có id : " + id.get());
    }

    @GetMapping("/api/todos")
    public void getTodos(@RequestParam Map<String, String> params) {
        log.info("Parameters are " + params.entrySet());
    }

    // Multi-value
    @GetMapping("/api/blogs")
    public void getBlogs(@RequestParam List<String> id) {
        log.info("id = " + id);
    }
}
