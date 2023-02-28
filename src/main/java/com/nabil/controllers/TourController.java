package com.nabil.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nabil.models.Tour;
import com.nabil.services.TourService;

import com.nabil.models.Place;
import com.nabil.services.PlaceService;

import com.nabil.models.Traveller;
import com.nabil.services.TravellerService;

@RestController
@Controller
public class TourController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private TourService tourService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TravellerService travellerService;

    @GetMapping("/tours")
    public ModelAndView getAllTours() {
        logger.debug("request to GET tour list");
        ModelAndView modelAndView = new ModelAndView("tours_new");
        
        modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }

    @GetMapping("/tours/custom")
    public ModelAndView getCustomTours() {
        logger.debug("request to GET tour list");
        ModelAndView modelAndView = new ModelAndView("tours_custom");
        
        //modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }

    @GetMapping("/tours/library")
    public ModelAndView getLibrary() {
        //logger.debug("request to GET tour list");
        ModelAndView modelAndView = new ModelAndView("tours_library");
        
        //modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }

    @GetMapping("/tours/featured")
    public ModelAndView getFeatured() {
        ModelAndView modelAndView = new ModelAndView("places_featured_1");
        return modelAndView;
    }

    @GetMapping("/tours/{id}")
    public ModelAndView getTour(@PathVariable Long id) {
        logger.debug("request to GET a tour");
        ModelAndView modelAndView = new ModelAndView("tour");
        modelAndView.addObject("tour", tourService.getTour(id));
        return modelAndView;
    }

    @GetMapping("/tours/book/{id}")
    public ModelAndView getBookingTour(@PathVariable Long id) {
        //logger.debug("request to GET a tour");
        ModelAndView modelAndView = new ModelAndView("tour_booking");
        modelAndView.addObject("tour", tourService.getTour(id));
        Traveller traveller = new Traveller();
        modelAndView.addObject("traveller", traveller);
        return modelAndView;
    }
    
    @PostMapping("/tours/book/{id}")
    public ModelAndView bookTour(@ModelAttribute Traveller traveller) {
        travellerService.addTraveller(traveller);
        ModelAndView modelAndView = new ModelAndView("tour_booking_success");
        return modelAndView;
    }

    /*@GetMapping("/tours/book")
    public ModelAndView getBookingTour() {
        //logger.debug("request to GET a tour");
        ModelAndView modelAndView = new ModelAndView("tour_booking");
        modelAndView.addObject("tour", tourService.getTour((long)231.00));
        Traveller traveller = new Traveller();
        modelAndView.addObject("traveller", traveller);
        return modelAndView;
    }

    @PostMapping("/tours/book")
    public ModelAndView bookTour() {
        //travellerService.addTraveller(traveller);
        ModelAndView modelAndView = new ModelAndView("tour_booking_success");
        return modelAndView;
    }*/

    
    
    @GetMapping("/tours/new")
    public ModelAndView tourCreationForm() {
        logger.debug("request to GET a new tour creation form");
        Tour tour = new Tour();
        ModelAndView modelAndView = new ModelAndView("tour_new_2");
        modelAndView.addObject("tour", tour);
        return modelAndView;
    }

    /*@GetMapping("/tours/location") 
    public ModelAndView getSpecificPlaceList() {
        ModelAndView modelAndView = new ModelAndView("tour_creation_success");
        //Place place = new Place();
        String location = "Bandarban";
        modelAndView.addObject("placesSpecific", placeService.getPlaceByLocation(location));
        
        return modelAndView;
    }*/

    @PostMapping("/tours/new")
    public ModelAndView addTour(@ModelAttribute Tour tour) {
        tourService.addTour(tour);
        logger.info("Tour Created");
        ModelAndView modelAndView = new ModelAndView("tour_creation_success");

        return modelAndView;
    }

    @PutMapping("tours/{id}")
    public void updateTour(@RequestBody Tour tour, @PathVariable Long id) {
        tourService.updateTour(tour, id);
    }

    @DeleteMapping("/tours/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }

    @DeleteMapping("/tours")
    public void deleteAllTours(@PathVariable Long id) {
        tourService.deleteAllTours();
    }


}
