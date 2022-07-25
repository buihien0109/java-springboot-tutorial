package vn.techmaster.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.course.repository.TopicRepository;
import vn.techmaster.course.service.CourseService;

@Controller
public class WebController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseService courseService;

    @GetMapping("/khoa-hoc")
    public String getListCoursePage(Model model,
                                    @RequestParam(required = false, defaultValue = "") String topic,
                                    @RequestParam(required = false, defaultValue = "") String name) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("courses", courseService.getAll(topic, name));
        return "web/course-list";
    }

    @GetMapping("/khoa-hoc/online")
    public String getOnlineCoursePage(Model model,
                                      @RequestParam(required = false, defaultValue = "") String topic,
                                      @RequestParam(required = false, defaultValue = "") String name) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("courses", courseService.getCoursesByType("online", topic, name));
        return "web/course-online-list";
    }

    @GetMapping("/khoa-hoc/onlab")
    public String getOnlabCoursePage(Model model,
                                     @RequestParam(required = false, defaultValue = "") String topic,
                                     @RequestParam(required = false, defaultValue = "") String name) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("courses", courseService.getCoursesByType("onlab", topic, name));
        return "web/course-onlab-list";
    }

    @GetMapping("/khoa-hoc/{id}/{slug}")
    public String getDetailCoursePage(@PathVariable int id, @PathVariable String slug, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "web/detail";
    }
}
