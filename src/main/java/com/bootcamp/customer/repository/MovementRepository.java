package com.bootcamp.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.customer.entity.Movement;

import java.util.Date;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
	List<Movement> findByAccountId(Long accountId);
	List<Movement> findByAccountIdAndDateBetween(Long accountId, Date from, Date to);
}