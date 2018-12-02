package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;

import java.util.EnumSet;

public enum Operator {
    BIGGER_THAN(">", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    BIGGER_THAN_OR_EQULAS(">=", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    SMALLER_THAN("<", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    EQUALS("=", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    LIKE("like", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    AND("and", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    },

    OR("or", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            return false;
        }
    };

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
