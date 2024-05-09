package com.example.vtravelwebappapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DestinationID")
    private Long destinationId;

    @Column(name = "DestinationName")
    private String destinationName;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "ImageURL")
    private String imageURL;

    // Constructors
    public Destination() {
    }

    public Destination(String destinationName, String description, String imageURL) {
        this.destinationName = destinationName;
        this.description = description;
        this.imageURL = imageURL;
    }

    // Getters and setters
    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    // toString method
    @Override
    public String toString() {
        return "Destination{" +
                "destinationId=" + destinationId +
                ", destinationName='" + destinationName + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
