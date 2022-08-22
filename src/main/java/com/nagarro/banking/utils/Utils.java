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

	public static Date getDate(String textDate) {
		SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = frmt.parse(textDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
