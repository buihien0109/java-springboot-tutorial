package com.example.coursebackend.mapper;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.response.CourseDetailResponse;
import com.example.coursebackend.service.SupporterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseMapper {

    private final SupporterService supporterService;

    public CourseDetailResponse toCourseDetail(Course course) {
        CourseDetailResponse courseDetailResponse = new CourseDetailResponse();
        courseDetailResponse.setId(course.getId());
        courseDetailResponse.setTitle(course.getTitle());
        courseDetailResponse.setDescription(course.getDescription());
        courseDetailResponse.setType(course.getType());
        courseDetailResponse.setImage(course.getImage());
        courseDetailResponse.setRating(course.getRating());
        courseDetailResponse.setTopics(course.getTopics());
        courseDetailResponse.setSupporter(supporterService.findById(course.getSupporterId()));

        return courseDetailResponse;
    }
}
