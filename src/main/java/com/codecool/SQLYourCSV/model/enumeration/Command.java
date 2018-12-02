package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.enumeration.helpers.OperatorValues;
import com.codecool.SQLYourCSV.model.enumeration.helpers.Selector;

public enum Command {
    SELECT("select", false, new Selector<String[]>(), new Rule[] {Rule.SELECT_RULE_1}),
    FROM("from", false, new Selector<String>(), new Rule[] {Rule.FROM_RULE_1, Rule.FROM_RULE_2}),
    WHERE("where", true, new Selector<OperatorValues>(), new Rule[] {Rule.WHERE_RULE_1});

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
