package com.nabil.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nabil.models.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    public Place findByLocation(String location);
}
