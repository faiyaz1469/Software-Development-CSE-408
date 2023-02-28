package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nabil.models.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {
    
}
