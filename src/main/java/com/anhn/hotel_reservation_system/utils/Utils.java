package com.anhn.hotel_reservation_system.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate convertStringToLocalDate(String date, String format) {
        DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }
}
