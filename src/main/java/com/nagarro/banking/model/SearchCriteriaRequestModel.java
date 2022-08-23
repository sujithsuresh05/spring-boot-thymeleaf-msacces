package com.nagarro.banking.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteriaRequestModel {

    @NotNull
    private String accountId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    private String amountFrom;

    private String amountTo;

}
