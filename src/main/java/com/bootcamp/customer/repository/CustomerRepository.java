package com.bootcamp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    boolean existsByIdentification(String identification);

}
