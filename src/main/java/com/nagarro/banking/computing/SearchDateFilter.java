package com.nagarro.banking.computing;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.dto.StatementDto;
import com.nagarro.banking.security.IsAdmin;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;

@PreAuthorize("hasRole('ADMIN')")
public class SearchDateFilter implements SearchFilter {

    private final SearchCriteriaDto searchCriteriaDto;


    public SearchDateFilter(SearchCriteriaDto searchCriteriaDto) {
        this.searchCriteriaDto = searchCriteriaDto;
    }

    @Override
    public boolean filter(StatementDto statementDto) {
        return searchCriteriaDto.getStartDate().compareTo(statementDto.getTransactionDate()) < 0 && searchCriteriaDto.getEndDate().compareTo(statementDto.getTransactionDate()) > 0;
    }

}
