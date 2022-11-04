package vn.techmaster.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.techmaster.course.entity.Topic;
import vn.techmaster.course.exception.BadRequestException;
import vn.techmaster.course.exception.NotFoundException;
import vn.techmaster.course.repository.TopicRepository;
import vn.techmaster.course.request.UpsertTopicRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    // Lấy danh sách topic
    public List<Topic> getTopics() {
        return topicRepository.findAll();
    }

    // Lấy chi tiết topic
    public Topic getTopicById(Integer id) {
        return topicRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found topic with id = " + id);
        });
    }

    // Tạo topic
    public Topic createTopic(UpsertTopicRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Topic name is required");
        }

        if(topicRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BadRequestException("Topic name = " + request.getName() + " is existed");
        }

        Topic topic = new Topic();
        topic.setName(request.getName());

        return topicRepository.save(topic);
    }

    // Cập nhật topic
    public Topic updateTopic(Integer id, UpsertTopicRequest request) {
        if (request.getName() == null || request.getName().equals("")) {
            throw new BadRequestException("Topic name is required");
        }

        Topic topic = topicRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found topic with id = " + id);
        });

        topic.setName(request.getName());

        return topicRepository.save(topic);
    }

    // Xóa topic
    public void deleteTopic(Integer id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found topic with id = " + id);
        });

        topicRepository.delete(topic);
    }
}
