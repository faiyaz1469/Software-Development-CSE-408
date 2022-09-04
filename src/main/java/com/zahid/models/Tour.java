package com.zahid.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String title;
    
    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    private String startDate;
    
    @Getter
    @Setter
    private String endDate;
    
    @Getter
    @Setter
    private Integer capacity;
    
    @Getter
    @Setter
    private Double cost;
    
    @Getter
    @Setter
    private String image;
    

    public Tour() {
    }

    public Tour(String title, String description, String startDate, String endDate, Integer capacity,
            Double cost) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return String.format("Tour(id=%s, title=%s, description=%s, startDate=%s, endDate=%s, capacity=%s, cost=%s)", id, title, description, startDate, endDate, capacity, cost);
    }
    
}
