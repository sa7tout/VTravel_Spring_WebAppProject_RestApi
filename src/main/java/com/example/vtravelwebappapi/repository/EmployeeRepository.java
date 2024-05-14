package com.example.vtravelwebappapi.repository;

import com.example.vtravelwebappapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom query methods here if needed
}
