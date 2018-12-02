package com.codecool.SQLYourCSV.model.enumeration;

public enum Rule {
    SELECT_RULE_1(Command.FROM, null, false),
    FROM_RULE_1(null, Command.SELECT, false),
    FROM_RULE_2(Command.WHERE, Command.SELECT, true),
    WHERE_RULE_1(null, Command.FROM, false);

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
