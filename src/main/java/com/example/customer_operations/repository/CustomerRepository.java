package com.example.customer_operations.repository;

import com.example.customer_operations.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // You can define custom query methods if needed
}

