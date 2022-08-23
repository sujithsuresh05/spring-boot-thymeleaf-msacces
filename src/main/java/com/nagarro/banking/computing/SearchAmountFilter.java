package com.nagarro.banking.computing;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.dto.StatementDto;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public class SearchAmountFilter implements SearchFilter {

    private SearchCriteriaDto searchCriteriaDto;

    public SearchAmountFilter(SearchCriteriaDto searchCriteriaDto) {
        this.searchCriteriaDto = searchCriteriaDto;
    }

    @Override
    public boolean filter(StatementDto statementDto) {
        return searchCriteriaDto.getAmountFrom() < statementDto.getAmount() && searchCriteriaDto.getAmountTo() > statementDto.getAmount();
    }
}
