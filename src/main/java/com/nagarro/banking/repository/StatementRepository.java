package com.nagarro.banking.repository;


import com.nagarro.banking.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {
	List<Statement> findByAccountId(String id);
}
