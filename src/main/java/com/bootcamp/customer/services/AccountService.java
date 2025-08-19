package com.bootcamp.customer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.customer.entity.Account;
import com.bootcamp.customer.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    //post
    public Account createAccount(Account account) {
        String number = account.getNumber();
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }
        if (accountRepository.existsByNumber(number)) {
            throw new IllegalArgumentException("Account with this number already exists: " + number);
        }
        return accountRepository.save(account);
    }
    //put
    public Account updateAccount(Long id, Account data) {
        Account existing = accountRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        String newNumber = data.getNumber();
        if (newNumber == null || newNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }
        boolean numberChanged = !newNumber.equals(existing.getNumber());
        if (numberChanged && accountRepository.existsByNumber(newNumber)) {
            throw new IllegalArgumentException("Account with this number already exists: " + newNumber);
        }

        // update allowed fields
        existing.setNumber(newNumber);
        existing.setType(data.getType());
        existing.setOpeningBalance(data.getOpeningBalance());
        existing.setStatus(data.getStatus());
        existing.setCustomer(data.getCustomer()); // only if you allow reassigning ownership

        return accountRepository.save(existing);
    }

}
