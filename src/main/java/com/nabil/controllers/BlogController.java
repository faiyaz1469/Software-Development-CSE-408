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

import com.nabil.models.Blog;
import com.nabil.services.BlogService;

@RestController
@Controller
public class BlogController {
    
    private final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ModelAndView getAllBlogs() {
        logger.debug("request to GET blog list");
        ModelAndView modelAndView = new ModelAndView("blogs");
        
        modelAndView.addObject("blogs", blogService.getAllBlogs());
        
        return modelAndView;
    }

    @GetMapping("/blogs/new")
    public ModelAndView blogCreationForm() {
        Blog blog = new Blog();
        ModelAndView modelAndView = new ModelAndView("blog_new");
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/blogs/new")
    public ModelAndView addBlog(@ModelAttribute Blog blog) {
        blogService.addBlog(blog);
        logger.info("Blog Created");
        ModelAndView modelAndView = new ModelAndView("blog_creation_success");

        return modelAndView;
    }

    /*@GetMapping("/tours/custom")
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
    
    @GetMapping("/tours/new")
    public ModelAndView tourCreationForm() {
        logger.debug("request to GET a new tour creation form");
        Tour tour = new Tour();
        ModelAndView modelAndView = new ModelAndView("tour_new_2");
        modelAndView.addObject("tour", tour);
        return modelAndView;
    }

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
    }*/

    @DeleteMapping("/blogs")
    public void deleteAllBlogs(@PathVariable Long id) {
        blogService.deleteAllBlogs();
    }


}
