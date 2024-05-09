package com.example.vtravelwebappapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private Long bookingId;

    @Column(name = "CustomerID")
    private Long customerId;

    @Column(name = "VoyageID")
    private Long voyageId;

    @Column(name = "BookingDate")
    private Date bookingDate;

    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    // Constructors, getters, and setters
}
