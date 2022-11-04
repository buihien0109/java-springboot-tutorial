package vn.techmaster.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.request.UpsertTopicRequest;
import vn.techmaster.course.service.TopicService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("")
    public ResponseEntity<?> getTopics() {
        List<Topic> topics = topicService.getTopics();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Integer id) {
        Topic topic = topicService.getTopicById(id);
        return ResponseEntity.ok(topic);
    }

    @PostMapping("")
    public ResponseEntity<?> createTopic(@RequestBody UpsertTopicRequest request) {
        Topic topic = topicService.createTopic(request);
        return new ResponseEntity<>(topic, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Integer id, @RequestBody UpsertTopicRequest request) {
        Topic topic = topicService.updateTopic(id, request);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
