package com.solvd.automationpractice.utils;

public class StringUtils {

    private StringUtils() {}

    public static String getNumbersFromString(String value) {
        return value.replaceAll("[^0-9]", "");
    }

}
