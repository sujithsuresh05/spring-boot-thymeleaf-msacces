package com.nagarro.banking.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteriaRequestModel {

    private String accountId;

    private String startDate;

    private String endDate;

    private String amountFrom;

    private String amountTo;

}
