package com.bootcamp.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String number;
    private String type;
    private Float openingBalance;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
