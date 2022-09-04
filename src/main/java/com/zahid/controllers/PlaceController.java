package com.zahid.controllers;

import java.util.List;

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

import com.zahid.models.Place;
import com.zahid.services.PlaceService;

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

    @Autowired
    PlaceService placeService;

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

    @GetMapping("/places/add") 
    public ModelAndView placeAdditionForm() {
        ModelAndView modelAndView = new ModelAndView("place-add");

        Place place = new Place();
        
        modelAndView.addObject("place", place);
        
        return modelAndView;
    }

    @PostMapping("/places/add") 
    public String addPlace(@ModelAttribute Place place) {
        placeService.addPlace(place);
        
        return "index";
    }

}
