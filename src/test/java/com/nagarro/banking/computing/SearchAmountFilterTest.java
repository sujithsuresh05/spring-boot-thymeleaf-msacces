package com.nagarro.banking.computing;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.dto.StatementDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchAmountFilterTest {

    @Test
    void filter_returnTrue() throws ParseException {
        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.of(1, null, null, Double.parseDouble("100"), Double.parseDouble("1000"));
        SearchAmountFilter searchAmountFilter = new SearchAmountFilter(searchCriteriaDto);
        Date date = parseDate("22.02.2021", "dd.MM.yyyy");
        StatementDto statementDto = StatementDto.of(1, "$2a$10$zwdV6sUKZ5/eD/OaCu.JbOysDYdBb3U0eQiPMqBbWCKHAXoUQnCCS", "123", date, Double.parseDouble("500"), "22.02.2021");
        boolean result = searchAmountFilter.filter(statementDto);
        assertThat(result).isTrue();
    }
}
