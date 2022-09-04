package com.zahid.controllers;

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

import com.zahid.models.Tour;
import com.zahid.services.TourService;

@RestController
@Controller
public class TourController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public ModelAndView getAllTours() {
        logger.debug("request to GET tour list");
        ModelAndView modelAndView = new ModelAndView("tours");
        
        modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }

    @GetMapping("/tours/{id}")
    public ModelAndView getTour(@PathVariable Long id) {
        logger.debug("request to GET a tour");
        ModelAndView modelAndView = new ModelAndView("tour");
        modelAndView.addObject("tour", tourService.getTour(id));
        return modelAndView;
    }
    
    @GetMapping("/tours/new")
    public ModelAndView tourCreationForm() {
        logger.debug("request to GET a new tour creation form");
        Tour tour = new Tour();
        ModelAndView modelAndView = new ModelAndView("tour_new");
        modelAndView.addObject("tour", tour);
        return modelAndView;
    }

    @PostMapping("/tours/new")
    public String addTour(@ModelAttribute Tour tour) {
        tourService.addTour(tour);
        logger.info("Tour Created");

        return "index";
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
