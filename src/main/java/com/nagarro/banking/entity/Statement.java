package com.nagarro.banking.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "statement")
public class Statement {

	@Column(name = "id")
	@Id
	private Long id;

	@Column(name = "account_id")
	private String accountId;

	@Column(name = "datefield")
	private String dateField;

	private String amount;


}
