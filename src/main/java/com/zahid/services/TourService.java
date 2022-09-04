package com.zahid.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zahid.models.Tour;
import com.zahid.repositories.TourRepository;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository; // handle for performing crud   

    public List<Tour> getAllTours() {
        List<Tour> tourList = new ArrayList<>();
        tourRepository.findAll().forEach(tourList::add);
        return tourList;
    }

	public Tour getTour(Long id) {
		return tourRepository.findById(id).get();
	}

    public void addTour(Tour tour) {
        tourRepository.save(tour);
    }

	public void deleteTour(Long id) {
        tourRepository.deleteById(id);
	}

    public void updateTour(Tour updateTour, Long id) {
        Tour tour = tourRepository.findById(id).get();
        tour = updateTour; // changing reference to new tour
        tourRepository.save(tour);
    }

	public void deleteAllTours() {
        tourRepository.deleteAll();
	}

}
