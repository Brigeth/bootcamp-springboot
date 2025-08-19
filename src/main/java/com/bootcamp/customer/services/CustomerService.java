package com.bootcamp.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.customer.entity.Customer;
import com.bootcamp.customer.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // POST customer
    public Customer registerCustomer(Customer customer) {
        String idn = customer.getIdentification();
        if (idn == null || idn.isBlank()) {
            throw new IllegalArgumentException("Identification is required");
        }
        if (customerRepository.existsByIdentification(idn)) {
            throw new IllegalArgumentException("Customer with this identification already exists: " + idn);
        }
        return customerRepository.save(customer);
    }

    // PUT customer
    public Customer updateCustomer(Long id, Customer data) {
        Customer existing = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        String newId = data.getIdentification();
        if (newId == null || newId.isBlank()) {
            throw new IllegalArgumentException("Identification is required");
        }
        boolean idChanged = !newId.equals(existing.getIdentification());
        if (idChanged && customerRepository.existsByIdentification(newId)) {
            throw new IllegalArgumentException("Customer with this identification already exists: " + newId);
        }

        // Update allowed fields
        existing.setIdentification(newId);
        existing.setPassword(data.getPassword());
        existing.setStatus(data.getStatus());

        return customerRepository.save(existing);
    }
}
