package vn.techmaster.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.demo.model.Tour;
import vn.techmaster.demo.service.TravelService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class TravelController {

    private TravelService travelService;

    @GetMapping("/")
    public String getHome(Model model) {
        List<Tour> tours = travelService.getAllTour().stream().limit(3).toList();
        model.addAttribute("tours", tours);
        return "index";
    }

    @GetMapping("/hotel")
    public String getHotel(Model model) {
        model.addAttribute("hotels", travelService.getAllHotel());
        return "hotel";
    }

    @GetMapping("/tour")
    public String getTour(Model model) {
        model.addAttribute("tours", travelService.getAllTour());
        return "tour";
    }

    @GetMapping("/rentar")
    public String getRentar(Model model) {
        model.addAttribute("rentars", travelService.getAllRentar());
        return "rentar";
    }
}
