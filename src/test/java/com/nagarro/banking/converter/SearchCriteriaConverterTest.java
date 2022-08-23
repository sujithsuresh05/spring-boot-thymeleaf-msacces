package com.nagarro.banking.converter;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class SearchCriteriaConverterTest {

    @Test
    void convertToDto_returnSearchCriteriaDto() {
        SearchCriteriaRequestModel searchCriteriaRequestModel = SearchCriteriaRequestModel.builder()
                .accountId("1").amountFrom("100").amountTo("1000").build();
        Converter<SearchCriteriaDto, SearchCriteriaRequestModel> converterCriteria = new SearchCriteriaConverter();
        SearchCriteriaDto result = converterCriteria.convertFromEntity(searchCriteriaRequestModel);
        SearchCriteriaDto expected = SearchCriteriaDto.of(1, null, null, Double.parseDouble("100"), Double.parseDouble("1000"));
        assertThat(result).isEqualToComparingFieldByField(expected);
    }

    @Test
    void convertFromEntity_returnSearchCriteriaDto() {
        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.of(1, null, null, Double.parseDouble("100"), Double.parseDouble("1000"));
        SearchCriteriaRequestModel expected = SearchCriteriaRequestModel.builder()
                .accountId("1").amountFrom("100.0").amountTo("1000.0").build();
        Converter<SearchCriteriaDto, SearchCriteriaRequestModel> converterCriteria = new SearchCriteriaConverter();
        SearchCriteriaRequestModel result = converterCriteria.convertFromDto(searchCriteriaDto);

        assertThat(result).isEqualToComparingFieldByField(expected);
    }

}
