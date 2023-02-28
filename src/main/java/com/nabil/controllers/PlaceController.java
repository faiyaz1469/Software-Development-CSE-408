package com.nabil.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nabil.models.Place;
import com.nabil.services.PlaceService;

@RestController
@Controller
public class PlaceController {
    /*
     * for this project root_url: localhost:8080 normally
     * 
     *  GET: root_url/tours -> getting all tours
     *  GET: root_url/tours/id -> getting a particular tour by id
     *  POST: root_url/tours -> creating a new tour
     *  PUT: root_url/tours/id -> updating a tour by id
     *  DELETE: root_url/tours/id -> deleteing a tour by id
     *  DELETE: root_url/tours -> deleting all tours
     */

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private PlaceService placeService;

    // @GetMapping("/places")
    // public List<Place> getAllPlaces() {
    //     return placeService.getAllPlaces();
    // }

    // @GetMapping("/places/{id}")
    // public Place getPlace(@PathVariable Long id) {
    //     return placeService.getPlace(id);
    // }

    // @PostMapping("/places")
    // public void addPlace(@RequestBody Place place) {
    //     placeService.addPlace(place);
    // }

    // @PutMapping("places/{id}")
    // public void updatePlace(@RequestBody Place place, @PathVariable Long id) {
    //     placeService.updatePlace(place, id);
    // }

    // @DeleteMapping("/places/{id}")
    // public void deletePlace(@PathVariable Long id) {
    //     placeService.deletePlace(id);
    // }

    // @DeleteMapping("/places")
    // public void deleteAllPlaces(@PathVariable Long id) {
    //     placeService.deleteAllPlaces();
    // }

    @GetMapping("/places/list") 
    public ModelAndView getPlaceList() {
        ModelAndView modelAndView = new ModelAndView("places");
        Place place = new Place();
        modelAndView.addObject("places", placeService.getAllPlaces());
        
        return modelAndView;
    }

    @GetMapping("/places/list/{id}") 
    public ModelAndView getPlace(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("places_specific");
        Place place = new Place();
        place = placeService.getPlace(id);
        List<Place> placeList = new ArrayList<>();
        placeList = placeService.getAllPlaces();
        
        
        /*List<Place> placeList2 = new ArrayList<>();
        List<Place> placeList3 = new ArrayList<>();
        List<Place> placeList4 = new ArrayList<>();
        
        for (Place place2 : placeList) {
            if(place.getLocation()==place2.getLocation()){
                placeList2.add(place2);
            }
            else{
                placeList4.add(place2);
            }
        }*/

        

        modelAndView.addObject("places", placeList);
        modelAndView.addObject("place_sp", place);
        
        return modelAndView;
    }

    /*@GetMapping("/tours/{id}")
    public ModelAndView getTour(@PathVariable Long id) {
        logger.debug("request to GET a tour");
        ModelAndView modelAndView = new ModelAndView("tour");
        modelAndView.addObject("tour", tourService.getTour(id));
        return modelAndView;
    }*/

    /*@GetMapping("/places/featured") 
    public ModelAndView getFeaturedPlace1(@PathVariable String location) {
        ModelAndView modelAndView = new ModelAndView("places_featured_1");
        return modelAndView;
    }*/

    /*@GetMapping("/places/location") 
    public ModelAndView getSpecificPlaceList(@PathVariable String location) {
        ModelAndView modelAndView = new ModelAndView("places_location");
        Place place = new Place();
        location = "Bandarban";
        modelAndView.addObject("placesSpecific", placeService.getPlaceByLocation(location));
        
        return modelAndView;
    }*/

    /*@GetMapping("/places/location2") 
    public ModelAndView getSpecificPlace() {
        ModelAndView modelAndView = new ModelAndView("places_location_2");
       // Place place = new Place();
        //location = "Bandarban";
        //modelAndView.addObject("placesSpecific", placeService.getPlaceName(location));
        
        return modelAndView;
    }*/

    @GetMapping("/places/add") 
    public ModelAndView placeAdditionForm() {
        ModelAndView modelAndView = new ModelAndView("place-add-2");

        Place place = new Place();
        
        modelAndView.addObject("place", place);
        
        return modelAndView;
    }

    @PostMapping("/places/add") 
    public ModelAndView addPlace(@ModelAttribute Place place) {
        ModelAndView modelAndView = new ModelAndView("place_creation_success");
        placeService.addPlace(place);
        
        return modelAndView;
    }

}
