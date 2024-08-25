package com.anhn.hotel_reservation_system.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate convertStringToLocalDate(String date, String format) {
        if(date == null || date.isEmpty() || format == null || format.isEmpty())
            return null;
        DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static String convertLocalDateToString(LocalDate date, String format) {
        if(date == null)
            return null;
        DateTimeFormatter.ofPattern(format);
        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
