package com.nagarro.banking.computing;

import com.nagarro.banking.dto.SearchCriteriaDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchFilterBuilder {

    public List<SearchFilter> build(SearchCriteriaDto searchCriteriaDto) {
        List<SearchFilter> searchFilterList = new ArrayList<>();
        this.setSearchDateFilter(searchCriteriaDto, searchFilterList)
                .setAmountFilter(searchCriteriaDto, searchFilterList)
                .setDefaultFilter(searchFilterList);
        return searchFilterList;

    }

    private SearchFilterBuilder setSearchDateFilter(SearchCriteriaDto searchCriteriaDto, List<SearchFilter> searchFilterList) {
        if (searchCriteriaDto.getStartDate() != null && searchCriteriaDto.getEndDate() != null) {
            searchFilterList.add(new SearchDateFilter(searchCriteriaDto));
        }
        return this;
    }

    private SearchFilterBuilder setAmountFilter(SearchCriteriaDto searchCriteriaDto, List<SearchFilter> searchFilterList) {
        if (searchCriteriaDto.getAmountFrom() != null && searchCriteriaDto.getAmountTo() != null) {
            searchFilterList.add(new SearchAmountFilter(searchCriteriaDto));
        }
        return this;
    }

    private void setDefaultFilter(List<SearchFilter> searchFilterList) {
        if (searchFilterList.isEmpty())
            searchFilterList.add(new DefaultFilter());
    }
}
