package com.nagarro.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor
public class SearchCriteriaDto {

    private final Integer accountId;

    private Date startDate;

    private Date endDate;

    private Double amountFrom;

    private Double amountTo;

}
