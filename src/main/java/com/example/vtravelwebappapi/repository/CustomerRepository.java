package com.example.vtravelwebappapi.repository;

import com.example.vtravelwebappapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
   // Customer findBySessionToken(String sessionToken);
}
