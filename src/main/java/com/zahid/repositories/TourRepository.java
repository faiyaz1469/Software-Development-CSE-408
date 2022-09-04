package com.zahid.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zahid.models.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {
    
}
