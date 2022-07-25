package com.example.pathvariable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
public class PathVariableController {
    @GetMapping("/foos/{id}")
    public void getFoo(@PathVariable String id) {
      log.info("foo id = " + id);
    }

    @GetMapping("/foos/{fooId}/bars/{barId}")
    public void getBar(@PathVariable String fooId, @PathVariable String barId) {
        log.info("foo id = " + fooId);
        log.info("bar id = " + barId);
    }

    @GetMapping("/users/{id}/blogs/{idOther}")
    public void getBog(@PathVariable(name = "id") String userId, @PathVariable(name = "idOther") String blogId) {
        log.info("user id = " + userId);
        log.info("blog id = " + blogId);
    }

    @GetMapping("/blogs/{blogId}/comments/{commentId}")
    public void getComment(@PathVariable Map<String, String> pathVarsMap) {
        log.info("blog id = " + pathVarsMap.get("blogId"));
        log.info("comment id = " + pathVarsMap.get("commentId"));
    }

    @GetMapping(value = {"/blogs", "blogs/{id}"})
    public void getBlog(@PathVariable(required = false) String id) {
        if(id != null) {
            log.info("blog id = " + id);
        } else {
            log.info("Không có id");
        }
    }

    @GetMapping(value = {"/users", "users/{id}"})
    public void getUser(@PathVariable(required = false) Optional<String> id) {
        if(id.isPresent()) {
            log.info("blog id = " + id.get());
        } else {
            log.info("Không có id");
        }
    }
}
