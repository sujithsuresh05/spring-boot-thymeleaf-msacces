package com.nagarro.banking.converter;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.dto.StatementDto;
import com.nagarro.banking.entity.Statement;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.utils.Constants;
import com.nagarro.banking.utils.Utils;

import java.util.function.Function;

public class SearchCriteriaConverter extends Converter<SearchCriteriaDto, SearchCriteriaRequestModel> {
    public SearchCriteriaConverter() {
        super(SearchCriteriaConverter::convertToEntity, SearchCriteriaConverter::convertToDto);
    }

    private static SearchCriteriaDto convertToDto(SearchCriteriaRequestModel searchCriteriaRequestModel) {
        return SearchCriteriaDto.of(searchCriteriaRequestModel.getAccountId() != null ? new Integer(searchCriteriaRequestModel.getAccountId()) : null,
                searchCriteriaRequestModel.getStartDate() != null ? Utils.getDate(searchCriteriaRequestModel.getStartDate(), Constants.UI_DATE_FORMAT) : null,
                searchCriteriaRequestModel.getEndDate() != null ? Utils.getDate(searchCriteriaRequestModel.getEndDate(), Constants.UI_DATE_FORMAT) : null,
                searchCriteriaRequestModel.getAmountFrom() != null ? new Double(searchCriteriaRequestModel.getAmountFrom()) : null,
                searchCriteriaRequestModel.getAmountTo() != null ? new Double(searchCriteriaRequestModel.getAmountTo()) : null);
    }

    private static SearchCriteriaRequestModel convertToEntity(SearchCriteriaDto searchCriteriaDto) {
        return new SearchCriteriaRequestModel(searchCriteriaDto.getAccountId() != null ? searchCriteriaDto.getAccountId().toString() : null,
                searchCriteriaDto.getStartDate() != null ? searchCriteriaDto.getStartDate().toString() : null,
                searchCriteriaDto.getEndDate() != null ? searchCriteriaDto.getEndDate().toString() : null,
                searchCriteriaDto.getAmountFrom() != null ? searchCriteriaDto.getAmountFrom().toString() : null,
                searchCriteriaDto.getAmountTo() != null ? searchCriteriaDto.getAmountTo().toString() : null);
    }
}
