package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.enumeration.helpers.OperatorValues;
import com.codecool.SQLYourCSV.model.enumeration.helpers.Selector;

import java.util.EnumSet;

public enum Command {
    SELECT("select", false, new Selector(), EnumSet.of(Rule.SELECT_RULE_1)),
    FROM("from", false, new Selector(), EnumSet.of(Rule.FROM_RULE_1, Rule.FROM_RULE_2)),
    WHERE("where", true, new Selector(), EnumSet.of(Rule.WHERE_RULE_1));

    private String name;
    private boolean isComparator;
    private Selector selector;
    private EnumSet<Rule> rules;

    Command(String name, boolean isComparator, Selector selector, EnumSet<Rule> rules) {
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


    public Selector selector() {
        return selector;
    }


    public EnumSet<Rule> rules() {
        return rules;
    }
}
