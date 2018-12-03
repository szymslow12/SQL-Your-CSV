package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.datastructure.Column;

public class OperatorValuesValidator {

    public static boolean areSameType(Column<?> base, Column<?> toCheck) {
        if (areColumnsNull(base, toCheck)) throw new IllegalArgumentException("Expect Column<?>: got null");
        return base.getValue().getClass().equals(toCheck.getValue().getClass());
    }


    public static boolean areNumbers(Column<?> toCheck, Column<?> toCheck1) {
        if (areColumnsNull(toCheck, toCheck1)) throw new IllegalArgumentException("Expect Column<?>: got null");
        return toCheck.getValue() instanceof Number && toCheck1.getValue() instanceof Number;
    }


    public static boolean areStrings(Column<?> toCheck, Column<?> toCheck1) {
        if (areColumnsNull(toCheck, toCheck1)) throw new IllegalArgumentException("Expect Column<?>: got null");
        return toCheck.getValue() instanceof String && toCheck1.getValue() instanceof String;
    }


    private static boolean areColumnsNull(Column<?> toCheck, Column<?> toCheck1) {
        return toCheck == null || toCheck1 == null;
    }
}
