package com.qa.mmoaccount.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.mmoaccount.domain.Account;
import com.qa.mmoaccount.service.ServiceInterface;

@RestController
@RequestMapping("/account")
public class AccountController {

	private ServiceInterface<Account> service; // Service

	public AccountController(ServiceInterface service) { // Controller
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) { // Create
		return new ResponseEntity<Account>(this.service.create(account), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Account>> readAllAccount() { // Read All
		return new ResponseEntity<List<Account>>(this.service.readAll(), HttpStatus.FOUND);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Account> readByIdAccount(@PathVariable Long id) { // Read Id
		return new ResponseEntity<Account>(this.service.readById(id), HttpStatus.FOUND);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) { // Update
		return new ResponseEntity<Account>(this.service.update(id, account), HttpStatus.ACCEPTED);
	}

	@Transactional
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable Long id) { // Delete
		return new ResponseEntity<Account>(this.service.delete(id), HttpStatus.OK);
	}
}