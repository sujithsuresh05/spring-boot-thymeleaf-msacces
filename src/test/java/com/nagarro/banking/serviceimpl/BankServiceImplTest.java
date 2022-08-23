package com.nagarro.banking.serviceimpl;

import com.nagarro.banking.computing.SearchChainingFilter;
import com.nagarro.banking.entity.Account;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.repository.AccountRepository;
import com.nagarro.banking.repository.StatementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceImplTest {
    @Mock
    private StatementRepository statementRepository;

    @Mock
    private SearchChainingFilter searchChainingFilter;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private BankServiceImpl subject;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void searchStatement_withSearchCriteriaRequestModel() {
        SearchCriteriaRequestModel searchCriteriaRequestModel = SearchCriteriaRequestModel.builder().accountId("1")
                .startDate("15.09.2010").endDate("15.09.2019").build();
        SearchResult expected = SearchResult.builder().date("Sun Nov 11 00:00:00 IST 2018").amount("1000.0").accountId("1").build();
        Statement statement = Statement.builder().id(1L).accountId("1").amount("1000").dateField("11.11.2018").build();
        when(searchChainingFilter.applyFilter(anyList(), any())).thenReturn(Boolean.TRUE);
        when(statementRepository.findByAccountId(anyString())).thenReturn(Collections.singletonList(statement));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(Account.builder().build()));
        List<SearchResult> result = subject.searchStatement(searchCriteriaRequestModel);
        assertThat(result.get(0)).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
