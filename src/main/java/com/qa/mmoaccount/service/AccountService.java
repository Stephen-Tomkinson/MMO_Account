package com.qa.mmoaccount.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.mmoaccount.domain.Account;
import com.qa.mmoaccount.repo.AccountRepo;

@Service
public class AccountService implements ServiceInterface<Account> {

	private AccountRepo accountRepo; // Repo

	public AccountService(AccountRepo accountRepo) { // Constructor
		this.accountRepo = accountRepo;
	}

	@Override
	public Account create(Account account) { // Create
		return this.accountRepo.save(account);
	}

	@Override
	public List<Account> readAll() { // Read All
		return this.accountRepo.findAll();
	}

	@Override
	public Account readById(Long id) { // Read Id
		Optional<Account> optionalAccount = this.accountRepo.findById(id);
		return optionalAccount.get();
	}

	@Override
	public Account update(Long id, Account account) { // Update
		Optional<Account> optionalAccount = this.accountRepo.findById(id);
		Account existingAccount = optionalAccount.get();
		existingAccount.setName(account.getName());
		existingAccount.setLevel(account.getLevel());
		existingAccount.setRegion(account.getRegion());
		return this.accountRepo.save(existingAccount);
	}

	public Account delete(Long id) { // Delete
		Optional<Account> toDelete = this.accountRepo.findById(id);
		this.accountRepo.deleteById(id);
		return toDelete.orElse(null);
	}
}
