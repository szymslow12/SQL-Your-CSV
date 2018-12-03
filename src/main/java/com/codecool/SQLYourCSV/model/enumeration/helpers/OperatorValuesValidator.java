package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.datastructure.Column;

public class OperatorValuesValidator {

    public static boolean isValid(Column<?> base, Column<?> toCheck) {
        return base.getValue().getClass().equals(toCheck.getValue().getClass());
    }
}
