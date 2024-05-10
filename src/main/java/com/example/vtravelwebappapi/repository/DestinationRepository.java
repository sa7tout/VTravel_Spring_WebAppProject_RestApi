package com.example.vtravelwebappapi.repository;

import com.example.vtravelwebappapi.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    // You can define custom query methods here if needed
}
