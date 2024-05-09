package com.example.vtravelwebappapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "voyages")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VoyageID")
    private Long voyageId;

    @Column(name = "DestinationID")
    private Long destinationId;

    @Column(name = "DepartureDate")
    private Date departureDate;

    @Column(name = "ReturnDate")
    private Date returnDate;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "AvailableSeats")
    private Integer availableSeats;

    // Constructors
    public Voyage() {
    }

    public Voyage(Long destinationId, Date departureDate, Date returnDate, BigDecimal price, Integer availableSeats) {
        this.destinationId = destinationId;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Getters and setters
    public Long getVoyageId() {
        return voyageId;
    }

    public void setVoyageId(Long voyageId) {
        this.voyageId = voyageId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "voyageId=" + voyageId +
                ", destinationId=" + destinationId +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
