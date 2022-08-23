package com.nagarro.banking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
@Builder
public class SearchResult {

    private String accountId;

    private String accountNumber;

    private String date;

    private String amount;

}
