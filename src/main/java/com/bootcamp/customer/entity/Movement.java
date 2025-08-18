package com.bootcamp.customer.entity;

import java.sql.Date;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Movement {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Date date;
    private String type;
    private Float amount;
    private Float balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
