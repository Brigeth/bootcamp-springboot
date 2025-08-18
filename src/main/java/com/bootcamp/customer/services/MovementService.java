package com.bootcamp.customer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.customer.entity.Account;
import com.bootcamp.customer.entity.Movement;
import com.bootcamp.customer.repository.AccountRepository;
import com.bootcamp.customer.repository.MovementRepository;

@Service
public class MovementService {
     @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountRepository accountRepository;

    // F2: Registrar un movimiento (depósito/retiro)
    public Movement registerMovement(Long accountId, Movement movement) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        // Validar retiro
        if ("Withdrawal".equalsIgnoreCase(movement.getType())) {
            if (account.getOpeningBalance() < movement.getAmount()) {
                throw new IllegalArgumentException("Insufficient balance");
            }
            account.setOpeningBalance(account.getOpeningBalance() - movement.getAmount());
        } else if ("Deposit".equalsIgnoreCase(movement.getType())) {
            account.setOpeningBalance(account.getOpeningBalance() + movement.getAmount());
        }

    movement.setBalance(account.getOpeningBalance());
    movement.setAccount(account); // Asignar la cuenta al movimiento
    movementRepository.save(movement);
    accountRepository.save(account);
    return movement;
    }

    // F3: Consultar movimientos por cuenta 
    public List<Movement> getMovementsByAccount(Long accountId) {
        return movementRepository.findByAccountId(accountId);
    }


    // Actualizar un movimiento
    public Movement updateMovement(Long id, Movement movement) {
        Movement existing = movementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Movement not found"));
        // Solo actualiza campos permitidos
        existing.setType(movement.getType());
        existing.setAmount(movement.getAmount());
        existing.setDate(movement.getDate());
        // No se recomienda cambiar el balance manualmente aquí
        return movementRepository.save(existing);
    }

    // Eliminar un movimiento
    public void deleteMovement(Long id) {
        if (!movementRepository.existsById(id)) {
            throw new IllegalArgumentException("Movement not found");
        }
        movementRepository.deleteById(id);
    }

    // Obtener todos los movimientos
    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    // Obtener un movimiento por id
    public Movement getMovementById(Long id) {
        return movementRepository.findById(id).orElse(null);
    }

}
