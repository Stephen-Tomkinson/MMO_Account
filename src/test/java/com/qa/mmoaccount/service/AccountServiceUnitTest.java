package com.qa.mmoaccount.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.mmoaccount.domain.Account;
import com.qa.mmoaccount.repo.AccountRepo;

@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceUnitTest {

	@Autowired
	private AccountService service;

	@MockBean
	private AccountRepo repo;

	private Account newAccount;
	private Account savedAccount;

	@BeforeEach
	void setUp() {
		newAccount = new Account("Nam", 1700, "EU");
		savedAccount = new Account(1L, "Nam", 1700, "EU");
	}

	@Test
	void createTest() {
		Mockito.when(this.service.create(newAccount)).thenReturn(savedAccount);
		assertEquals(savedAccount, this.service.create(newAccount));
		Mockito.verify(this.repo, Mockito.times(1)).save(newAccount);
	}

	@Test
	void readAllTest() {
		List<Account> mockInput = new ArrayList<Account>();
		assertEquals(mockInput, this.service.readAll());
		mockInput.add(new Account(1L, "Nam", 1700, "EU"));
		mockInput.add(new Account(2L, "Bren", 1800, "NA"));
		Mockito.when(this.repo.findAll()).thenReturn(mockInput);
		assertEquals(mockInput, this.service.readAll());
	}

	@Test
	void readByIdTest() {
		Long id = 1L;
		Optional<Account> optAccount = Optional.of(new Account(id, null, 0, null));
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		assertEquals(optAccount.get(), this.service.readById(id));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void updateTest() {
		Long id = 1L;
		Account toUpdate = new Account("Bruky", 1100, "EU");
		Optional<Account> optAccount = Optional.of(new Account(id, null, 0, null));
		Account updated = new Account(id, toUpdate.getName(), toUpdate.getLevel(), toUpdate.getRegion());
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		assertEquals(updated, this.service.update(id, toUpdate));
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	void deleteTest() {
		Long id = 1L;
		Optional<Account> optAccount = Optional.of(new Account(id, null, 0, null));
		Account deleted = optAccount.get();
		Mockito.when(this.repo.findById(id)).thenReturn(optAccount);
		assertEquals(deleted, this.service.delete(id));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
}