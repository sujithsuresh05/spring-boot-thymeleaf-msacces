package com.nagarro.banking.service;

import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;

import java.util.List;

public interface BankService {

     List<SearchResult> searchStatement( SearchCriteriaRequestModel searchCriteriaRequestModel);

}
