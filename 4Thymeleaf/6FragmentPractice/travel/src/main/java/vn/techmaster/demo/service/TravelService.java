package vn.techmaster.demo.service;

import vn.techmaster.demo.model.Hotel;
import vn.techmaster.demo.model.Rentar;
import vn.techmaster.demo.model.Tour;

import java.util.List;

public interface TravelService {
    void init();
    List<Tour> getAllTour();
    List<Hotel> getAllHotel();
    List<Rentar> getAllRentar();
}
