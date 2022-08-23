package com.nagarro.banking.converter;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.utils.Constants;
import com.nagarro.banking.utils.Utils;
import org.apache.commons.lang3.StringUtils;

public class SearchCriteriaConverter extends Converter<SearchCriteriaDto, SearchCriteriaRequestModel> {
    public SearchCriteriaConverter() {
        super(SearchCriteriaConverter::convertToEntity, SearchCriteriaConverter::convertToDto);
    }

    private static SearchCriteriaDto convertToDto(SearchCriteriaRequestModel searchCriteriaRequestModel) {
        return SearchCriteriaDto.of(Integer.valueOf(searchCriteriaRequestModel.getAccountId()),
                searchCriteriaRequestModel.getStartDate() != null ? Utils.getDate(searchCriteriaRequestModel.getStartDate(), Constants.UI_DATE_FORMAT) : null,
                searchCriteriaRequestModel.getEndDate() != null ? Utils.getDate(searchCriteriaRequestModel.getEndDate(), Constants.UI_DATE_FORMAT) : null,
                StringUtils.isNotEmpty(searchCriteriaRequestModel.getAmountFrom()) ? Double.valueOf(searchCriteriaRequestModel.getAmountFrom()) : null,
                StringUtils.isNotEmpty(searchCriteriaRequestModel.getAmountTo()) ? Double.valueOf(searchCriteriaRequestModel.getAmountTo()) : null);
    }

    private static SearchCriteriaRequestModel convertToEntity(SearchCriteriaDto searchCriteriaDto) {
        return new SearchCriteriaRequestModel(searchCriteriaDto.getAccountId().toString(),
                searchCriteriaDto.getStartDate() != null ? searchCriteriaDto.getStartDate().toString() : null,
                searchCriteriaDto.getEndDate() != null ? searchCriteriaDto.getEndDate().toString() : null,
                searchCriteriaDto.getAmountFrom() != null ? searchCriteriaDto.getAmountFrom().toString() : null,
                searchCriteriaDto.getAmountTo() != null ? searchCriteriaDto.getAmountTo().toString() : null);
    }
}
