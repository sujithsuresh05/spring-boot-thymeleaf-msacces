package com.nagarro.banking.repository;



import com.nagarro.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
//	Optional<Account> findById(String acctId);
}
