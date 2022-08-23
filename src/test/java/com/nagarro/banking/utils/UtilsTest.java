package com.nagarro.banking.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.parseDate;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {

    @Test
    void getDate_withStringDateAndPattern_returnDate() throws ParseException {
        String textDate = "2022-12-22";
        String pattern = "yyyy-MM-dd";
        Date expected = parseDate("2022-12-22", "yyyy-MM-dd");
        Date result = Utils.getDate(textDate, pattern);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getDouble_returnSuccess() {
        Double result = Utils.getDouble("10");
        assertThat(result).isEqualTo(10d);
    }
}
