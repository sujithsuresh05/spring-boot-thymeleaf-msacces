package com.nagarro.banking.serviceimpl;

import com.nagarro.banking.computing.SearchChainingFilter;
import com.nagarro.banking.computing.SearchDateFilter;
import com.nagarro.banking.computing.SearchFilter;
import com.nagarro.banking.computing.SearchFilterBuilder;
import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.entity.Account;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.repository.AccountRepository;
import com.nagarro.banking.repository.StatementRepository;
import com.nagarro.banking.utils.Constants;
import com.nagarro.banking.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceImplTest {
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

    @Mock
    private SearchFilterBuilder searchFilterBuilder;

    @Test
    void searchStatement_withSearchCriteriaRequestModel() {
        SearchCriteriaRequestModel searchCriteriaRequestModel = SearchCriteriaRequestModel.builder().accountId("1")
                .startDate("15.09.2010").endDate("15.09.2019").build();
        SearchResult expected = SearchResult.builder().date("11.11.2018").amount("1000.0").accountId("1").build();
        Statement statement = Statement.builder().id(1L).accountId("1").amount("1000").dateField("11.11.2018").build();
        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.builder().accountId(1).
                startDate(Utils.getDate("15.09.2010", Constants.UI_DATE_FORMAT)).
                endDate(Utils.getDate("15.09.2019", Constants.UI_DATE_FORMAT))
                .build();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilters.add(new SearchDateFilter(searchCriteriaDto));
        when(searchFilterBuilder.build(any())).thenReturn(searchFilters);
        when(searchChainingFilter.applyFilter(anyList(), any())).thenReturn(Boolean.TRUE);
        when(statementRepository.findByAccountId(anyString())).thenReturn(Collections.singletonList(statement));
        when(accountRepository.findById(1L)).thenReturn(Optional.of(Account.builder().build()));
        List<SearchResult> result = subject.searchStatement(searchCriteriaRequestModel);
        assertThat(result.get(0)).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
