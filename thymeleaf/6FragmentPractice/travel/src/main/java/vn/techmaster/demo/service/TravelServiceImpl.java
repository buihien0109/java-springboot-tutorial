package vn.techmaster.demo.service;

import org.springframework.stereotype.Service;
import vn.techmaster.demo.model.Hotel;
import vn.techmaster.demo.model.Rentar;
import vn.techmaster.demo.model.Tour;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelServiceImpl implements TravelService{
    private List<Tour> tours;
    private List<Hotel> hotels;
    private List<Rentar> rentars;

    public TravelServiceImpl() {
        init();
    }

    @Override
    public void init() {
        tours = new ArrayList<>();
        tours.add(new Tour(1, "3 Days 2 Nights in Hoi An Old Town – VietNam Tour", "Hoi An tours are ideal for travellers looking to experience the local culture and...", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/1-2-800x600.png"));
        tours.add(new Tour(2, "Travel to Danang City – The Most Livable City in Vietnam", "Da Nang is a city of bridges, growing rapidly with resort construction attempting redefine...", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/5-1-800x600.png"));
        tours.add(new Tour(3, "Hue City Sightseeing Tour with Huong River Cruise", "Hue is the national city of Vietnam and used to be the capital of...", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/7-800x600.png"));
        tours.add(new Tour(4, "Sapa Trekking Tours – 2 Days 1 Night Stay At Homestay", "Varius massa maecenas et id dictumst mattis. Donec fringilla ac parturient posuere ...", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/10-800x600.png"));
        tours.add(new Tour(5, "Half-Day Chiang Mai City and Temples Tour Including Doi Suthep", "Arius massa maecenas et id dictumst mattis. Donec fringilla ac parturient posuere id phasellus erat ... ", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/12-800x600.png"));
        tours.add(new Tour(6, "Tourism Paradise Island Koh Rong Cambodia 2 Days 1 Night", "Arius massa maecenas et id dictumst mattis. Donec fringilla ac parturient posuere id phasellus erat ...", "https://shinetheme.com/travelerdata/solotour/wp-content/uploads/2015/01/11-800x600.png"));

        hotels = new ArrayList<>();
        hotels.add(new Hotel(1, "EnVision Hotel Boston", "Boston, MA, USA", 4.5, 40.00, "https://mixmap.travelerwp.com/wp-content/uploads/2014/12/167411799-680x630.jpg"));
        hotels.add(new Hotel(2, "Hotel Stanford", "Boston, MA, USA", 4, 35.00, "https://mixmap.travelerwp.com/wp-content/uploads/2014/12/74264099-680x630.jpg"));
        hotels.add(new Hotel(3, "Parian Holiday Villas", "Virginia, USA", 3.7, 38.50, "https://mixmap.travelerwp.com/wp-content/uploads/2014/12/169474401-680x630.jpg"));
        hotels.add(new Hotel(4, "Dylan Hotel", "New York City, NY, USA", 2.9, 26.00, "https://mixmap.travelerwp.com/wp-content/uploads/2014/12/60337007-680x630.jpg"));

        rentars = new ArrayList<>();
        rentars.add(new Rentar(1, "LUXURY APARTMENT", "Virginia Beach, VA, USA", 2, 1, 1, 30.00, "https://mixmap.travelerwp.com/wp-content/uploads/2015/01/CLL181_TAKE_01_20_of_21-740x560.jpg"));
        rentars.add(new Rentar(2, "STYLISH 2 BEDROOMS", "Ohio, USA", 4, 2, 2, 50.00, "https://mixmap.travelerwp.com/wp-content/uploads/2015/01/CLL181_TAKE_01_4_of_21-740x560.jpg"));
        rentars.add(new Rentar(3, "MANHATTAN OVERSIZED", "Wilmington, NC, USA", 5, 3, 2, 70.00, "https://mixmap.travelerwp.com/wp-content/uploads/2015/01/CLL181_TAKE_01_5_of_21-1-740x560.jpg"));
        rentars.add(new Rentar(4, "LUXURY STUDIO", "Virginia Beach, VA, USA", 3, 1, 1, 35.00, "https://mixmap.travelerwp.com/wp-content/uploads/2015/01/81Hudson-0042-740x560.jpg"));
    }

    @Override
    public List<Tour> getAllTour() {
        return tours;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotels;
    }

    @Override
    public List<Rentar> getAllRentar() {
        return rentars;
    }
}
