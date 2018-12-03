package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.enumeration.Operator;

public class OperatorValues {

    private Operator operator;
    private String[] toCompare;

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


    public Operator getOperator() {
        return operator;
    }


    public void setToCompare(String[] toCompare) {
        this.toCompare = toCompare;
    }


    public String[] getToCompare() {
        return toCompare;
    }
}
