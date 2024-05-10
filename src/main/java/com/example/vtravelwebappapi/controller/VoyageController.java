package com.example.vtravelwebappapi.controller;

import com.example.vtravelwebappapi.model.Voyage;
import com.example.vtravelwebappapi.repository.VoyageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class VoyageController {

    private static final Logger logger = LoggerFactory.getLogger(VoyageController.class);

    private final VoyageRepository voyageRepository;

    @Autowired
    public VoyageController(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    @GetMapping("/api/voyages")
    public ResponseEntity<List<Voyage>> getVoyagesBySearch(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam("destination") Long destinationId,
            @RequestParam("numPeople") Integer numPeople) {

        logger.info("Received request for voyages between {} and {}", startDate, endDate);
        logger.info("Destination ID: {}, Number of people: {}", destinationId, numPeople);

        // Ensure that the time component of the dates is set to midnight
        startDate = setTimeToMidnight(startDate);
        endDate = setTimeToMidnight(endDate);

        logger.debug("Adjusted startDate: {}, endDate: {}", startDate, endDate);

        // Query the database to fetch voyages available during the specified date range
        List<Voyage> voyages = voyageRepository.findByDepartureDateBetween(startDate, endDate);

        // Filter voyages based on destination and available seats
        List<Voyage> filteredVoyages = new ArrayList<>();
        for (Voyage voyage : voyages) {
            if (voyage.getDestinationId().equals(destinationId) && voyage.getAvailableSeats() >= numPeople) {
                filteredVoyages.add(voyage);
            }
        }

        if (filteredVoyages.isEmpty()) {
            logger.info("No voyages found matching the criteria");
            return new ResponseEntity<>(filteredVoyages, HttpStatus.OK);
        }

        logger.info("Found {} voyages matching the criteria", filteredVoyages.size());


        // Print each voyage
        for (Voyage voyage : filteredVoyages) {
            logger.info("{}", voyage);
        }

        return new ResponseEntity<>(filteredVoyages, HttpStatus.OK);
    }



    // Helper method to set the time component of a date to midnight (00:00:00.000)
    private Date setTimeToMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @GetMapping("/api/voyages/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable("id") Long voyageId) {
        logger.info("Received request for voyage with ID: {}", voyageId);

        // Search for the voyage in the repository
        Optional<Voyage> optionalVoyage = voyageRepository.findById(voyageId);

        if (optionalVoyage.isPresent()) {
            Voyage voyage = optionalVoyage.get();
            logger.info("Voyage found: {}", voyage);
            return new ResponseEntity<>(voyage, HttpStatus.OK);
        } else {
            logger.info("Voyage with ID {} not found", voyageId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
