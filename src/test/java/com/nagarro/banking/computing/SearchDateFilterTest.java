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
class SearchDateFilterTest {

    @Test
    void filter_returnTrue() throws ParseException {
        Date date = parseDate("22.02.2021", "dd.MM.yyyy");
        Date startDate = parseDate("21.02.2021", "dd.MM.yyyy");
        Date endDate = parseDate("25.02.2021", "dd.MM.yyyy");
        StatementDto statementDto = StatementDto.of(1, null, "123", date, Double.parseDouble("500"), "22.02.2021");
        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.of(1, startDate, endDate, Double.parseDouble("100"), Double.parseDouble("1000"));
        SearchDateFilter searchDateFilter = new SearchDateFilter(searchCriteriaDto);
        boolean result = searchDateFilter.filter(statementDto);
        assertThat(result).isTrue();
    }
}
