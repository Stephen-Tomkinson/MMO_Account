package com.qa.mmoaccount.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.mmoaccount.domain.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {

}
