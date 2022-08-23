package com.nagarro.banking.computing;

import com.nagarro.banking.dto.StatementDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchChainingFilter {

    public boolean applyFilter(List<SearchFilter> searchFilterList, StatementDto statementDto) {
       return searchFilterList.stream().allMatch( chain -> {
            return  chain.filter(statementDto);
        });
    }
}
