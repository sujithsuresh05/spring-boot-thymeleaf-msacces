package com.nagarro.banking.serviceimpl;

import com.nagarro.banking.computing.*;
import com.nagarro.banking.converter.Converter;
import com.nagarro.banking.converter.SearchCriteriaConverter;
import com.nagarro.banking.converter.StatementConverter;
import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.dto.StatementDto;
import com.nagarro.banking.entity.Account;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.exception.InvalidAccountException;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.repository.AccountRepository;
import com.nagarro.banking.repository.StatementRepository;
import com.nagarro.banking.service.BankService;
import com.nagarro.banking.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final StatementRepository statementRepository;

    private final AccountRepository accountRepository;

    private final SearchChainingFilter searchChainingFilter;

    private final BCryptPasswordEncoder passwordEncoder;

    private final SearchFilterBuilder searchFilterBuilder;

    @Override
    public List<SearchResult> searchStatement(SearchCriteriaRequestModel searchCriteriaRequestModel) {

        SearchCriteriaDto searchCriteriaDto = getSearchCriteriaDto(searchCriteriaRequestModel);
        List<StatementDto> statementDtoList = getStatementDtoList(searchCriteriaDto);
        List<SearchFilter> searchFilterList = searchFilterBuilder.build(searchCriteriaDto);

        List<StatementDto> statementDtoList1 = statementDtoList.stream().filter(dto ->
                searchChainingFilter.applyFilter(searchFilterList, dto)
        ).collect(Collectors.toList());

        return statementDtoList1.stream().map(dto ->
                SearchResult.of(Integer.toString(dto.getAccountId()),
                        dto.getAccountNumberIdHashed(), dto.getTransactionDateText(), Double.toString(dto.getAmount()))
        ).collect(Collectors.toList());
    }

    /**
     * Get all the statement from the repository against the given account id
     * convert statement to dto using converter
     */
    private List<StatementDto> getStatementDtoList(SearchCriteriaDto searchCriteriaDto) {
        List<Statement> statementList = statementRepository.findByAccountId(searchCriteriaDto.getAccountId().toString());
        Converter<StatementDto, Statement> converter = new StatementConverter(passwordEncoder);
        return converter.createFromEntities(statementList);
    }

    private SearchCriteriaDto getSearchCriteriaDto(SearchCriteriaRequestModel searchCriteriaRequestModel) {
        Long accountId = new Double(searchCriteriaRequestModel.getAccountId()).longValue();
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent())
            throw new InvalidAccountException(accountId, Constants.INVALID_ACCOUNT_ERROR_MSG);

        Converter<SearchCriteriaDto, SearchCriteriaRequestModel> converterCriteria = new SearchCriteriaConverter();
        return converterCriteria.convertFromEntity(searchCriteriaRequestModel);
    }

}

