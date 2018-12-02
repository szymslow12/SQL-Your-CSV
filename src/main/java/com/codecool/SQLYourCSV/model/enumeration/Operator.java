package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;

import java.util.EnumSet;

public enum Operator {
    ;

    private String value;
    private EnumSet<Command> belongsTo;

    Operator(String value, EnumSet<Command> belongsTo) {
        this.value = value;
        this.belongsTo = belongsTo;
    }


    public String value() {
        return value;
    }


    public EnumSet<Command> belongsTo() {
        return belongsTo;
    }


    public abstract boolean compare(Column<?> base, Column<?> toCompare);
}
