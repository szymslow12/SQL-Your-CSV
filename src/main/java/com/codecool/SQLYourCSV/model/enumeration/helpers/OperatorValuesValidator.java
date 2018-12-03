package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.datastructure.Column;

public class OperatorValuesValidator {

    public static boolean areSameType(Column<?> base, Column<?> toCheck) {
        return base.getValue().getClass().equals(toCheck.getValue().getClass());
    }


    public static boolean areNumbers(Column<?> toCheck, Column<?> toCheck1) {
        return toCheck.getValue() instanceof Number && toCheck1.getValue() instanceof Number;
    }


    public static boolean areStrings(Column<?> toCheck, Column<?> toCheck1) {
        return toCheck.getValue() instanceof String && toCheck1.getValue() instanceof String;
    }
}
