package com.nagarro.banking.computing;

import com.nagarro.banking.dto.StatementDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p> Project have a good implementation to provide searching on date and integer
 * fields that stored in ms access DB in text format .</p>
 * <p> The implementation is following filter chaining model so that it can be
 * extended easily when new Type search field come </p>
 */
@Component
public class SearchChainingFilter {

    public boolean applyFilter(List<SearchFilter> searchFilterList, StatementDto statementDto) {
       return searchFilterList.stream().allMatch( chain -> chain.filter(statementDto));
    }
}
