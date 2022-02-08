package com.qa.mmoaccount.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
@ActiveProfiles("test")
public class AccountDomainUnitTest {

	@Test
	void noArgsTest() {
		Account account = new Account();
		// ???
	}

	@Test
	void allArgsNoIdTest() {
		Account account = new Account("Nam", 1700, "EU");
		assertNotNull(account.getName());
		assertNotNull(account.getLevel());
		assertNotNull(account.getRegion());
		assertEquals("Nam", account.getName());
		assertEquals(1700, account.getLevel());
		assertEquals("EU", account.getRegion());
	}

	@Test
	void allArgsTest() {
		Account account = new Account(1L, "Nam", 1700, "EU");
		assertNotNull(account.getId());
		assertNotNull(account.getName());
		assertNotNull(account.getLevel());
		assertNotNull(account.getRegion());
		assertEquals((Long) 1L, account.getId());
		assertEquals("Nam", account.getName());
		assertEquals(1700, account.getLevel());
		assertEquals("EU", account.getRegion());
	}

	@Test
	void settersTest() {
		Account account = new Account("Nam", 1700, "EU");
		account.setId(2L);
		account.setName("Bren");
		account.setLevel(1800);
		account.setRegion("NA");
		assertEquals((Long) 2L, account.getId());
		assertEquals("Bren", account.getName());
		assertEquals(1800, account.getLevel());
		assertEquals("NA", account.getRegion());
	}

	@Test
	void equalsHashTest() {
		EqualsVerifier.forClass(Account.class).usingGetClass().verify();
	}

	@Test
	void toStringTest() {
		Account account = new Account(1L, "Nam", 1700, "EU");
		assertEquals("Account [id=1, name=Nam, level=1700, region=EU]", account.toString());
	}
}