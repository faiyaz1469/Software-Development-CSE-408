package com.zahid.seeder;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.zahid.models.Tour;
import com.zahid.services.TourService;

@Component
public class TourSeeder implements CommandLineRunner {
    private static String DATA_URL = "https://raw.githubusercontent.com/digital-animal/xplorebd-files/main/tours.csv";

    private final Logger logger = LoggerFactory.getLogger(TourSeeder.class);
    
    @Autowired
    private TourService tourService;

    @Override
    public void run(String... args) throws Exception {
        loadSeedData();
    }

    // loading seeded data from csv file hosted in github
    public void loadSeedData() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        List<Tour> tours = tourService.getAllTours();
        
        if(tours.size() == 0) {

            for (CSVRecord record : records) {
                // Province/State,Country/Region,Lat,Long
                String title = record.get("Title");
                String description = record.get("Description");
                String startDate = record.get("Start Date");
                String endDate = record.get("End Date");
                Integer capacity = Integer.parseInt(record.get("Capacity"));
                Double cost = Double.parseDouble(record.get("Cost"));
                String image = record.get("Image");

                Tour tour = new Tour(title,description,startDate,endDate,capacity,cost);
                tour.setImage(image);
                
                tourService.addTour(tour);
            }
            
            logger.info("Number of tours : {}", tourService.getAllTours().size());

        }

    }
}
