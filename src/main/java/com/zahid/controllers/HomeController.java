package com.zahid.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zahid.services.PlaceService;
import com.zahid.services.TourService;

@Controller
public class HomeController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    TourService tourService;

    @Autowired
    PlaceService placeService;
    
    @GetMapping("/") 
    public ModelAndView home() {
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        
        modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }
    
    @GetMapping("/tours/list") 
    public ModelAndView index() {
        logger.debug("request to GET list");
        ModelAndView modelAndView = new ModelAndView("tours");
        
        modelAndView.addObject("tours", tourService.getAllTours());
        
        return modelAndView;
    }
        
    // @GetMapping("/places/list") 
    // public ModelAndView places() {
    //     logger.debug("request to GET list");
    //     ModelAndView modelAndView = new ModelAndView("places");
        
    //     modelAndView.addObject("places", placeService.getAllPlaces());
        
    //     return modelAndView;
    // }
    
    @GetMapping("/user")
    public String user(Model model) {
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }
}
