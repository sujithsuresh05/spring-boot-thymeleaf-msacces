package com.nagarro.banking.serviceimpl;

import com.nagarro.banking.entity.Account;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.repository.AccountRepository;
import com.nagarro.banking.repository.StatementRepository;
import com.nagarro.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BankServiceImpl implements BankService {

    private StatementRepository statementRepository;

    private AccountRepository accountRepository;

    @Autowired
    public BankServiceImpl(StatementRepository statementRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.statementRepository = statementRepository;
    }

    @Override
    public List<SearchResult> searchStatement(SearchCriteriaRequestModel searchCriteriaRequestModel) {

        Optional<Account> accountOptional = accountRepository.findById(new Long(1));
        List<Statement> statementList = statementRepository.findByAccountId(1 + "");
        Random random = new Random();
        /**
         * Dummy search list initializing
         */
        List<SearchResult> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(SearchResult.of(new Integer(i).toString(), new Integer(random.nextInt(1000000)).toString(),
                    "00-00-00", "20000"));
        }
        return list;
    }
}

