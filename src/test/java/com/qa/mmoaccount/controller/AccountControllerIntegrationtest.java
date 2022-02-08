package com.qa.mmoaccount.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.mmoaccount.domain.Account;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:account-schema.sql",
		"classpath:account-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) // pre-populate
@ActiveProfiles("test")
public class AccountControllerIntegrationtest {

	@Autowired
	private MockMvc mock; // Mock Requests

	@Autowired
	private ObjectMapper map; // Interprets JSON

	@Test
	void testCreateCont() throws Exception {
		// ----request
		Account newAcc = new Account("Schaf", 1300, "EU"); // Create Account
		String newAJSON = this.map.writeValueAsString(newAcc); // Convert into JSON
		RequestBuilder mockRequest = post("/account/create").contentType(MediaType.APPLICATION_JSON).content(newAJSON);
		// ^^ build the mock request ^^ // --response
		Account savedA = new Account(2L, "Schaf", 1300, "EU"); // alredy inserted one record on line 1
		String savedAJSON = this.map.writeValueAsString(savedA); // convert to JSON
		// --test response
		ResultMatcher matchStatus = status().isCreated(); // test status = 201-CREATED
		ResultMatcher matchBody = content().json(savedAJSON); // test result body
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody); // perform the test
	}

	@Test
	void readAllTest() throws Exception {
		Account readAcc = new Account(1L, "Nam", 1700, "EU");
		String readAccJSON = this.map.writeValueAsString(readAcc);
		Long IDread = 1L;
		RequestBuilder readReq = get("/account/getById/" + IDread).contentType(MediaType.APPLICATION_JSON)
				.content(readAccJSON);
		ResultMatcher status = status().isFound();
		ResultMatcher body = content().json(readAccJSON);
		this.mock.perform(readReq).andExpect(status).andExpect(body);
	}

	@Test
	void ReadByIdTest() throws Exception {
		Account readAcc = new Account(1L, "Nam", 1700, "EU");
		List<Account> allAcc = List.of(readAcc);
		String readAccJSON = this.map.writeValueAsString(allAcc);
		RequestBuilder readReq = get("/account/getAll");
		ResultMatcher status = status().isFound();
		ResultMatcher body = content().json(readAccJSON);
		this.mock.perform(readReq).andExpect(status).andExpect(body);
	}

	@Test
	void updateTest() throws Exception {
		Account updateAcc = new Account("Tabby", 1100, "EU");
		String updateAccJSON = this.map.writeValueAsString(updateAcc);
		Long IDupdate = 1L;
		RequestBuilder updateReq = put("/account/update/" + IDupdate).contentType(MediaType.APPLICATION_JSON)
				.content(updateAccJSON);
		Account retUpdatedAcc = new Account(1L, "Tabby", 1100, "EU");
		String retUpdatedAccJSON = this.map.writeValueAsString(retUpdatedAcc);
		ResultMatcher retStatus = status().isAccepted();
		ResultMatcher retBody = content().json(retUpdatedAccJSON);
		this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
	}

	@Test
	void deleteTest() throws Exception {
		Account deleteAcc = new Account(1L, "Nam", 1700, "EU");
		String deleteAccJSON = this.map.writeValueAsString(deleteAcc);
		Long delId = 1L;
		RequestBuilder delRequest = delete("/account/delete/" + delId);
		ResultMatcher Status = status().isOk();
		ResultMatcher Body = content().json(deleteAccJSON);
		this.mock.perform(delRequest).andExpect(Status).andExpect(Body);
	}
}
