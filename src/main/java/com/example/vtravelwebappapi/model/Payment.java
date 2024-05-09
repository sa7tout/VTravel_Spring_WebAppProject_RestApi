package com.example.vtravelwebappapi.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private Long paymentId;

    @Column(name = "BookingID")
    private Long bookingId;

    @Column(name = "PaymentDate")
    private Date paymentDate;

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "PaymentMethod")
    private String paymentMethod;

    // Constructors, getters, and setters
}
