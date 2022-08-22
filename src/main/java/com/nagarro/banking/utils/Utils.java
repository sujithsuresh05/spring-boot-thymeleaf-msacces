package com.nagarro.banking.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static Double getDouble(String value) {
		Double doubleValue = StringUtils.isNotEmpty(value) ? Double.parseDouble(value) : 0;
		return doubleValue;
	}

	public static Date getDate(String textDate, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern == null ? "yyyy-MM-dd" : pattern);
		Date date = null;
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
