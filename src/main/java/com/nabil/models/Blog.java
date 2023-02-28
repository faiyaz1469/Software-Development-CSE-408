package com.nabil.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Blog {
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
    private String description;
    

    public Blog() {
    }

    public Blog(String name, String title, String description) {
        this.name = name;
        this.title = title;
        this.description = description;
    }


    @Override
    public String toString() {
        return String.format("Tour(id=%s, name=%s, title=%s, description=%s, startDate=%s, endDate=%s, capacity=%s, cost=%s)", id, name, title, description);
    }
    
}
