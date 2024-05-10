package com.example.vtravelwebappapi.service;

import com.example.vtravelwebappapi.model.Destination;
import com.example.vtravelwebappapi.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public String getDestinationNameById(long id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            return optionalDestination.get().getDestinationName();
        } else {
            return null; // or throw an exception, depending on your error handling strategy
        }
    }

    public String getDestinationImagePathById(long id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            return optionalDestination.get().getImageURL();
        } else {
            return null; // or throw an exception, depending on your error handling strategy
        }
    }
}
