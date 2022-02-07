package com.qa.mmoaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.mmoaccount.domain.Account;
import com.qa.mmoaccount.repo.AccountRepo;

@Service
public class AccountService implements ServiceInterface<Account> {

	// Repo
	private AccountRepo accountRepo;

	// Constructor
	public AccountService(AccountRepo accountRepo) {
		this.accountRepo = accountRepo;
	}

	// Create
	@Override
	public Account create(Account account) {
		return this.accountRepo.save(account);
	}

	// Read [All - ID]
	@Override
	public List<Account> readAll() { // All
		return this.accountRepo.findAll();
	}

	@Override
	public Account readById(Long id) { // Id
		Optional<Account> optionalAccount = this.accountRepo.findById(id);
		return optionalAccount.get();
	}

	// Update
	@Override
	public Account update(Long id, Account account) {
		Optional<Account> optionalAccount = this.accountRepo.findById(id);
		Account existingAccount = optionalAccount.get();
		existingAccount.setName(account.getName());
		existingAccount.setLevel(account.getLevel());
		existingAccount.setRegion(account.getRegion());
		return this.accountRepo.save(existingAccount);
	}

	// Delete
	public Account delete(Long id) {
		Optional<Account> toDelete = this.accountRepo.findById(id);
		this.accountRepo.deleteById(id);
		return toDelete.orElse(null);
	}
}
