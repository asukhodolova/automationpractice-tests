package com.solvd.automationpractice.utils;

import java.util.List;

public class DataUtils {

    private DataUtils() {
    }

    public static Object[][] listToDataProviderArray(List<?> list) {
        if (list.size() == 0) {
            return new Object[][]{};
        }
        Object[][] data = new Object[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }
        return data;
    }
}
