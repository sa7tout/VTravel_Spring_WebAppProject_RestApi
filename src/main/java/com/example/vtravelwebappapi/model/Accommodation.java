package com.example.vtravelwebappapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccommodationID")
    private Long accommodationId;

    @Column(name = "DestinationID")
    private Long destinationId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "PricePerNight")
    private Double pricePerNight;

    // Constructors, getters, and setters
}
