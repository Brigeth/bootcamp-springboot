package com.bootcamp.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.customer.entity.Account;
import com.bootcamp.customer.repository.AccountRepository;
import com.bootcamp.customer.repository.CustomerRepository;
import com.bootcamp.customer.entity.Customer;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id).orElse(null);
    }


    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        // Validar que el cliente existe antes de asociar
        if (account.getCustomer() == null || account.getCustomer().getId() == null) {
            throw new IllegalArgumentException("The 'customer.id' field is required");
        }
        Customer customer = customerRepository.findById(account.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("The customer with id " + account.getCustomer().getId() + " does not exist"));
        account.setCustomer(customer);
        return accountRepository.save(account);
    }


    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setId(id);
        return accountRepository.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }

    // Buscar cuenta por número
    @GetMapping("/number/{number}")
    public Account getAccountByNumber(@PathVariable String number) {
        return accountRepository.findByNumber(number).orElse(null);
    }

    // Listar cuentas por id de cliente
    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

}
