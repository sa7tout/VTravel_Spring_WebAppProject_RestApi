package com.example.vtravelwebappapi.repository;

import com.example.vtravelwebappapi.model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    // Define a custom query method to fetch voyages by departure date between two dates
    List<Voyage> findByDepartureDateBetween(Date startDate, Date endDate);
}
