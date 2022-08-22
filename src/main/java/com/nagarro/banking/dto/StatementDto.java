package com.nagarro.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(staticName = "of")
public class StatementDto {

    private Integer accountId;

    private String accountNumberIdHashed;

    private String accountNumber;

    private Date transactionDate;

    private Double amount;


}
