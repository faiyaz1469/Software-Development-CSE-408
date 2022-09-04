package com.zahid.repositories;

import org.springframework.data.repository.CrudRepository;

import com.zahid.models.Place;

public interface PlaceRepository extends CrudRepository<Place, Long> {
    
}
