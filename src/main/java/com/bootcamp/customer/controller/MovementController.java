package com.bootcamp.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.bootcamp.customer.entity.Movement;
import com.bootcamp.customer.services.MovementService;

@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {

	@Autowired
	private MovementService movementService;

	@GetMapping
	public List<Movement> getAllMovements() {
		return movementService.getAllMovements();
	}

	// Obtener un movimiento por id
	@GetMapping("/{id}")
	public Movement getMovementById(@PathVariable Long id) {
		return movementService.getMovementById(id);
	}


	// Consultar movimientos por cuenta
	@GetMapping("/account/{accountId}")
	public List<Movement> getMovementsByAccount(@PathVariable Long accountId) {
		return movementService.getMovementsByAccount(accountId);
	}


	// Registrar un movimiento (depósito/retiro)
	@PostMapping("/account/{accountId}")
	public Movement registerMovement(@PathVariable Long accountId, @RequestBody Movement movement) {
		return movementService.registerMovement(accountId, movement);
	}

	@PutMapping("/{id}")
	public Movement updateMovement(@PathVariable Long id, @RequestBody Movement movement) {
		return movementService.updateMovement(id, movement);
	}

	@DeleteMapping("/{id}")
	public void deleteMovement(@PathVariable Long id) {
		movementService.deleteMovement(id);
	}
}
