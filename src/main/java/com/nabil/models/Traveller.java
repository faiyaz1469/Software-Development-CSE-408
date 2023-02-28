package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Traveller {
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
    private String title;
    
    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private Double totalcost;
    

    public Traveller() {
    }

    public Traveller(String name, Integer quantity, String title, Double totalcost) {
        this.name = name;
        this.quantity = quantity;
        this.title = title;
        this.totalcost = totalcost;
    }


    @Override
    public String toString() {
        return String.format("Traveller(id=%s, name=%s, quantity=%s, title=%s, totalcost=%s)", id, name, quantity, title, totalcost);
    }
    
}
