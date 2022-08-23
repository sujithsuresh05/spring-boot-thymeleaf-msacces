package com.nagarro.banking.validator;

import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.utils.Constants;
import com.nagarro.banking.utils.Utils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

import static com.nagarro.banking.utils.Constants.UI_DATE_FORMAT;

@Component("searchFormValidator")
public class SearchFormValidator implements Validator {



	@Override
	public boolean supports(Class<?> paramClass) {
		return SearchCriteriaRequestModel.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		SearchCriteriaRequestModel searchCriteria = (SearchCriteriaRequestModel) obj;

		Double amountFrom = Utils.getDouble(searchCriteria.getAmountFrom());
		Double amountTo = Utils.getDouble(searchCriteria.getAmountTo());
		Date start = searchCriteria.getStartDate() != null ? Utils.getDate(searchCriteria.getStartDate(), UI_DATE_FORMAT) : null;
		Date end = searchCriteria.getEndDate() != null ? Utils.getDate(searchCriteria.getEndDate(), UI_DATE_FORMAT) : null;
			if (amountFrom.doubleValue() < 0) {
				errors.rejectValue("amountFrom", "negativeValue", new Object[] { "'amountFrom'" },
						Constants.NEGATE_AMOUNT_ERROR);
			}

			if (amountTo.doubleValue() < 0) {
				errors.rejectValue("amountTo", "negativeValue", new Object[] { "'amountTo'" },
						Constants.NEGATE_AMOUNT_ERROR);
			}

			if (start != null && end != null) {
				if (start.compareTo(end) >= 0) {
					errors.rejectValue("startDate", "dateSelction", new Object[] { "'startDate'" },
							Constants.FROM_DATE_GREATER_THAN_TO_DATE_ERROR);
				}
			}
			else if ((start == null || end == null) && (start != null || end != null)) {
				errors.rejectValue("startDate", "dateSelction", new Object[] { "'startDate'" },
						Constants.FROM_DATE_TO_DATE_NULL_ERROR);
			}

		if (amountFrom > amountTo) {
				errors.rejectValue("amountFrom", "negativeValue", new Object[] { "'amountFrom'" },
						Constants.START_AMOUNT_GREATER_THAN_END_AMOUNT);
			}


	}

}
