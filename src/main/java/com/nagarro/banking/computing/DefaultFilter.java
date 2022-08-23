package com.nagarro.banking.computing;

import com.nagarro.banking.dto.StatementDto;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;

@PreAuthorize("hasRole('USER')")
public class DefaultFilter implements SearchFilter {

    private final Date startDate;
    private final Date endDate;


    public DefaultFilter() {
        this.startDate = DateUtils.addMonths(new Date(), -3);
        this.endDate = new Date();
    }

    @Override
    public boolean filter(StatementDto statementDto) {
        return this.startDate.compareTo(statementDto.getTransactionDate()) < 0 && this.endDate.compareTo(statementDto.getTransactionDate()) > 0;
    }
}
