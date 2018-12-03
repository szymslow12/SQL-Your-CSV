package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.datastructure.Column;

public class OperatorValuesValidator {

    public static boolean areSameType(Column<?> base, Column<?> toCheck) {
        if (areColumnsNull(base, toCheck))
            throw new IllegalArgumentException("Expect Column<?>: got null");
        return base.getValue().getClass().equals(toCheck.getValue().getClass());
    }


    public static boolean areNumbers(Column<?> toCheck, Column<?> toCheck1) {
        if (areColumnsNull(toCheck, toCheck1))
            throw new IllegalArgumentException("Expect Column<?>: got null");
        return toCheck.getValue() instanceof Number && toCheck1.getValue() instanceof Number;
    }


    public static boolean areStrings(Column<?> toCheck, Column<?> toCheck1) {
        if (areColumnsNull(toCheck, toCheck1))
            throw new IllegalArgumentException("Expect Column<?>: got null");
        return toCheck.getValue() instanceof String && toCheck1.getValue() instanceof String;
    }


    public static boolean areBooleans(Column<?> toCheck, Column<?> toCheck1) {
        if (areColumnsNull(toCheck, toCheck1))
            throw new IllegalArgumentException("Expect Column<?>: got null");
        return toCheck.getValue() instanceof Boolean && toCheck1.getValue() instanceof Boolean;
    }


    public static boolean areSelectedType(Column<?> toCheck, Column<?> toCheck1, Class selectedType) {
        if (areColumnsNull(toCheck, toCheck1) || selectedType == null)
            throw new IllegalArgumentException("Expect Column<?> or Class: got null");

        return toCheck.getValue().getClass().equals(selectedType) &&
                toCheck1.getValue().getClass().equals(selectedType);
    }


    private static boolean areColumnsNull(Column<?> toCheck, Column<?> toCheck1) {
        return toCheck == null || toCheck1 == null || toCheck.getValue() == null || toCheck1.getValue() == null;
    }
}
