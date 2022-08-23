package com.nagarro.banking.model;

import com.nagarro.banking.validator.AmountValueMatch;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

import static com.nagarro.banking.utils.Constants.START_AMOUNT_GREATER_THAN_END_AMOUNT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@AmountValueMatch(
        message = START_AMOUNT_GREATER_THAN_END_AMOUNT,
        field = "amountFrom",
        fieldMatch = "amountTo"
)
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
