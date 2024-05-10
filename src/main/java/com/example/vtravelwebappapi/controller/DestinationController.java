package com.example.vtravelwebappapi.controller;

import com.example.vtravelwebappapi.model.Destination;
import com.example.vtravelwebappapi.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }
    @GetMapping("/{id}/name")
    public String getDestinationNameById(@PathVariable("id") int id) {
        return destinationService.getDestinationNameById(id);
    }
    @GetMapping("/{id}/image")
    public String getDestinationImagePathById(@PathVariable("id") int id) {
        return destinationService.getDestinationImagePathById(id);
    }
}
