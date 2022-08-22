package com.nagarro.banking.computing;

import com.nagarro.banking.dto.StatementDto;

public interface SearchFilter {

    public boolean filter(StatementDto statementDto);
}
