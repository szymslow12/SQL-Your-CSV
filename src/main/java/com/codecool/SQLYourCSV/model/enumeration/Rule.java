package com.codecool.SQLYourCSV.model.enumeration;

public enum Rule {
    ;

    private Command goesBefore;
    private Command goesAfter;
    private boolean isOptional;

    Rule(Command goesBefore, Command goesAfter, boolean isOptional) {
        this.goesBefore = goesBefore;
        this.goesAfter = goesAfter;
        this.isOptional = isOptional;
    }


    public Command goesBefore() {
        return goesBefore;
    }


    public Command goesAfter() {
        return goesAfter;
    }


    public boolean isOptional() {
        return isOptional;
    }
}
