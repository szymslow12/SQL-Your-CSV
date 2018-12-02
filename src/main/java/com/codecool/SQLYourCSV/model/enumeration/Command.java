package com.codecool.SQLYourCSV.model.enumeration;

public enum Command {
    SELECT(),
    FROM(),
    WHERE();

    private String name;
    private boolean isComparator;
    private Selector<?> selector;
    private Rule[] rules;

    Command(String name, boolean isComparator, Selector<?> selector, Rule[] rules) {
        this.name = name;
        this.isComparator = isComparator;
        this.selector = selector;
        this.rules = rules;
    }


    public String getName() {
        return name;
    }


    public boolean isComparator() {
        return isComparator;
    }


    public Selector<?> selector() {
        return selector;
    }


    public Rule[] rules() {
        return rules;
    }
}
