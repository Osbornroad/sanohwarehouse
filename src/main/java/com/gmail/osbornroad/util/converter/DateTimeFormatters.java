package com.gmail.osbornroad.util.converter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.gmail.osbornroad.util.DateTimeUtil.parseLocalDate;
import static com.gmail.osbornroad.util.DateTimeUtil.parseLocalTime;
import static com.gmail.osbornroad.util.DateTimeUtil.parseLocalDateTime;

/**
 * gkislin
 * 25.10.2016
 */
public class DateTimeFormatters {

   /* public static class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

        @Override
        public LocalDateTime parse(String s, Locale locale) throws ParseException {
            return parseLocalDateTime(s);
        }

        @Override
        public String print(LocalDateTime localDateTime, Locale locale) {
            return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }*/

    public static class LocalDateFormatter implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String text, Locale locale) throws ParseException {
            return parseLocalDate(text);
        }

        @Override
        public String print(LocalDate lt, Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static class LocalTimeFormatter implements Formatter<LocalTime> {

        @Override
        public LocalTime parse(String text, Locale locale) throws ParseException {
            return parseLocalTime(text);
        }

        @Override
        public String print(LocalTime lt, Locale locale) {
            return lt.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
}