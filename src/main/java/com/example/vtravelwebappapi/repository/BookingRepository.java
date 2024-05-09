package com.example.vtravelwebappapi.repository;

import com.example.vtravelwebappapi.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // You can define custom query methods here if needed
}
