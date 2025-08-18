package com.bootcamp.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.customer.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByNumber(String number);
    List<Account> findByCustomerId(Long customerId);
} 
