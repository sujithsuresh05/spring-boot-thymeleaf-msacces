package com.nagarro.banking.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class Utils {

	private Utils() {
	}

	public static Double getDouble(String value) {
		return StringUtils.isNotEmpty(value) ? Double.parseDouble(value) : 0;
	}

	public static Date getDate(String textDate, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern == null ? "yyyy-MM-dd" : pattern);
		Date date = null;
		try {
			date = format.parse(textDate);
		} catch (ParseException e) {
			log.error(e.getMessage(), e);
		}
		return date;
	}
}
