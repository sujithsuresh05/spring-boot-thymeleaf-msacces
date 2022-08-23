package com.nagarro.banking.computing;

import com.nagarro.banking.dto.SearchCriteriaDto;
import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.utils.Constants;
import com.nagarro.banking.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
 class SearchFilterBuilderTest {



    @InjectMocks
    private SearchFilterBuilder searchFilterBuilder;

    @Test
    void buildSearchFilter_withSearchCriteriaRequestModel() {

        SearchCriteriaDto searchCriteriaDto = SearchCriteriaDto.builder().accountId(1).
                startDate(Utils.getDate("15.09.2010", Constants.UI_DATE_FORMAT)).
                endDate(Utils.getDate("15.09.2019", Constants.UI_DATE_FORMAT))
                .build();
        SearchDateFilter expected = new SearchDateFilter(searchCriteriaDto);
        List<SearchFilter> searchFilterList = searchFilterBuilder.build(searchCriteriaDto);
        assertThat(searchFilterList.get(0)).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
