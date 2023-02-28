package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private String location;
    
    @Getter
    @Setter
    private String image;
    

    public Place() {
    }

    public Place(String name, String type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }


    @Override
    public String toString() {
        return String.format("Place(id=%s, name=%s, type=%s, location=%s)", id, name, type, location);
    }
    
}
