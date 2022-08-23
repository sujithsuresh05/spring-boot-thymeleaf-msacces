package com.nagarro.banking.utils;

public final class Constants {

	public static final String SEARCH_CRITERIA_VALIDATOR_QUALIFIER_NAME = "searchFormValidator";
	public static final String ERROR_LOGIN = "Invalid Username or Password";
	
	public static final String BAD_CREDENTIAL = "Bad Credential";
	
	public static final String SESSION_ERROR = "Oops Session got expired!!";
	
	public static final String BIND_ERROR = "Returning search page with errors";
	
	public static final String NEGATE_AMOUNT_ERROR = "Amount can't be negative";

	public static final String FROM_DATE_GREATER_THAN_TO_DATE_ERROR = "To date must be greater than from date";

	public static final String FROM_DATE_TO_DATE_NULL_ERROR = "To date and from date can not be null";
	
	public static final String START_AMOUNT_GREATER_THAN_END_AMOUNT = "Start amount must be less than than end amount";
	
	public static final String SEARCH_AUTHORIZATION_ERROR = "You are not authorized to search with parameter ...!";

	public static final String GLOBAL_ERROR_NAME = "global_error";

	public static final String INVALID_ACCOUNT_ERROR_MSG = "Provided Account Id is Invalid";

	public static final String UI_DATE_FORMAT = "dd.MM.yyyy";

	public static final String DEFAULT_ERROR_VIEW = "error";

	public static final String VALID_USER_ERROR_VIEW = "valid_user_error";

	public static final String LANDING_PAGE = "/bank/dashboard";

	private Constants() {

	}
}
