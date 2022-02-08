package com.qa.mmoaccount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.mmoaccount.domain.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}