package com.nagarro.banking.converter;

import com.nagarro.banking.dto.StatementDto;
import com.nagarro.banking.entity.Statement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StatementConverterTest {

    @Test
    void convertToDto_returnStatementDto() throws ParseException {
        Date date = parseDate("22.02.2021", "dd.MM.yyyy");
        StatementDto statementDto = StatementDto.of(1, "$2a$10$zwdV6sUKZ5/eD/OaCu.JbOysDYdBb3U0eQiPMqBbWCKHAXoUQnCCS", "123", date, Double.parseDouble("100"), "22.02.2021");
        Statement expected = Statement.builder().id(1L).accountId("123").amount("100.0").dateField("Mon Feb 22 00:00:00 IST 2021").build();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Converter<StatementDto, Statement> converter = new StatementConverter(passwordEncoder);
        Statement result = converter.convertFromDto(statementDto);
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }

    @Test
    void convertFromEntity_returnStatementDto() throws ParseException {
        Date date = parseDate("22.02.2021", "dd.MM.yyyy");
        StatementDto expected = StatementDto.of(1, null, "123", date, Double.parseDouble("100"), "22.02.2021");
        Statement statement = Statement.builder().id(1L).accountId("123").amount("100.0").dateField("22.02.2021").build();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Converter<StatementDto, Statement> converter = new StatementConverter(passwordEncoder);
        StatementDto result = converter.convertFromEntity(statement);
        expected.setAccountNumberIdHashed(result.getAccountNumberIdHashed());
        assertThat(result).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
