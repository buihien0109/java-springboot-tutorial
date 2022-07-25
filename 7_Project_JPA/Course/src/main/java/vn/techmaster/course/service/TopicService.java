package vn.techmaster.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.repository.TopicRepository;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAll() {
        return topicRepository.findAll();
    }
}
