package com.nagarro.banking.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="account")
public class Account {

	@Column(name = "ID")
	@Id
	private Long id;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "account_number")
	private String accountNumber;


}

