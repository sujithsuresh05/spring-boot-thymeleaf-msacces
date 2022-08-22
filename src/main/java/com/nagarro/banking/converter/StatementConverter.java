package com.nagarro.banking.converter;

import com.nagarro.banking.dto.StatementDto;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.utils.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.function.Function;

public class StatementConverter extends Converter<StatementDto, Statement> {
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public StatementConverter(BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(StatementConverter::convertToEntity, StatementConverter::convertToDto);
        StatementConverter.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private static StatementDto convertToDto(Statement statement) {
        return StatementDto.of( statement.getId().intValue(), bCryptPasswordEncoder.encode(statement.getAccountId()),
                statement.getAccountId(), Utils.getDate(statement.getDateField(), "dd.MM.yyyy"), Double.parseDouble(statement.getAmount()));
    }

    private static Statement convertToEntity(StatementDto dto) {
        return new Statement(dto.getAccountId().longValue(), new Integer(dto.getAccountNumber()).toString(),
                dto.getTransactionDate().toString(), new Double(dto.getAmount()).toString());
    }
}
