package com.nagarro.banking.serviceimpl;

import com.nagarro.banking.computing.SearchAmountFilter;
import com.nagarro.banking.computing.SearchChainingFilter;
import com.nagarro.banking.computing.SearchDateFilter;
import com.nagarro.banking.computing.SearchFilter;
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
import com.nagarro.banking.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final StatementRepository statementRepository;

    private final AccountRepository accountRepository;

    private final SearchChainingFilter searchChainingFilter;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<SearchResult> searchStatement( SearchCriteriaRequestModel searchCriteriaRequestModel) {

        Long accountId = new Double(searchCriteriaRequestModel.getAccountId()).longValue();
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (!accountOptional.isPresent())
            throw new InvalidAccountException(accountId, Constants.INVALID_ACCOUNT_ERROR_MSG);

        searchCriteriaRequestModel = new SearchCriteriaRequestModel("1", "15.09.2010",
                "15.09.2019", "100", "20000");
        Converter<SearchCriteriaDto, SearchCriteriaRequestModel> converterCriteria = new SearchCriteriaConverter();
        SearchCriteriaDto searchCriteriaDto = converterCriteria.convertFromEntity(searchCriteriaRequestModel);

        List<Statement> statementList = statementRepository.findByAccountId(searchCriteriaDto.getAccountId().toString());
        Converter<StatementDto, Statement> converter = new StatementConverter(passwordEncoder);
        List<StatementDto> statementDtoList = converter.createFromEntities(statementList);

        List<SearchFilter> searchFilterList = new ArrayList<>();

        searchFilterList.add(new SearchDateFilter(searchCriteriaDto));
        searchFilterList.add(new SearchAmountFilter(searchCriteriaDto));
        List<StatementDto> statementDtoList1 = statementDtoList.stream().filter(dto -> {
            return searchChainingFilter.applyFilter(searchFilterList, dto);
        }).collect(Collectors.toList());

        List<SearchResult> list = statementDtoList1.stream().map(dto -> {
            return SearchResult.of(Integer.toString(dto.getAccountId()),
                    dto.getAccountNumberIdHashed(), dto.getTransactionDate().toString(), Double.toString(dto.getAmount()));
        }).collect(Collectors.toList());
        return list;
    }
}

