package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import com.codecool.SQLYourCSV.model.enumeration.helpers.OperatorValuesValidator;

import java.util.EnumSet;

public enum Operator {
    BIGGER_THAN(">", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSameType(base, toCompare) &&
                OperatorValuesValidator.areNumbers(base, toCompare)) {

                return castToProperTypeAndCompare(base, toCompare);
            }
            throw new IllegalArgumentException("Expect same Type or Number Type: got different");
        }

        private boolean castToProperTypeAndCompare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSelectedType(base, toCompare, Integer.class)) {
                return (Integer) base.getValue() > (Integer) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Long.class)) {
                return (Long) base.getValue() > (Long) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Float.class)) {
                return (Float) base.getValue() > (Float) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Double.class)) {
                return (Double) base.getValue() > (Double) toCompare.getValue();
            } else {
                throw new IllegalArgumentException("Column Types not allowed!");
            }
        }

    },

    BIGGER_THAN_OR_EQUAL(">=", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSameType(base, toCompare) &&
                    OperatorValuesValidator.areNumbers(base, toCompare)) {

                return castToProperTypeAndCompare(base, toCompare);
            }
            throw new IllegalArgumentException("Expect same Type or Number Type: got different");
        }

        private boolean castToProperTypeAndCompare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSelectedType(base, toCompare, Integer.class)) {
                return (Integer) base.getValue() >= (Integer) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Long.class)) {
                return (Long) base.getValue() >= (Long) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Float.class)) {
                return (Float) base.getValue() >= (Float) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Double.class)) {
                return (Double) base.getValue() >= (Double) toCompare.getValue();
            } else {
                throw new IllegalArgumentException("Column Types not allowed!");
            }
        }
    },

    SMALLER_THAN("<", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSameType(base, toCompare) &&
                    OperatorValuesValidator.areNumbers(base, toCompare)) {

                return castToProperTypeAndCompare(base, toCompare);
            }
            throw new IllegalArgumentException("Expect same Type or Number Type: got different");
        }

        private boolean castToProperTypeAndCompare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSelectedType(base, toCompare, Integer.class)) {
                return (Integer) base.getValue() < (Integer) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Long.class)) {
                return (Long) base.getValue() < (Long) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Float.class)) {
                return (Float) base.getValue() < (Float) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Double.class)) {
                return (Double) base.getValue() < (Double) toCompare.getValue();
            } else {
                throw new IllegalArgumentException("Column Types not allowed!");
            }
        }
    },

    SMALLER_THAN_OR_EQUAL("<=", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSameType(base, toCompare) &&
                OperatorValuesValidator.areNumbers(base, toCompare)) {

                return castToProperTypeAndCompare(base, toCompare);
            }
            throw new IllegalArgumentException("Expect same Type or Number Type: got different");
        }

        private boolean castToProperTypeAndCompare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSelectedType(base, toCompare, Integer.class)) {
                return (Integer) base.getValue() <= (Integer) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Long.class)) {
                return (Long) base.getValue() <= (Long) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Float.class)) {
                return (Float) base.getValue() <= (Float) toCompare.getValue();
            } else if (OperatorValuesValidator.areSelectedType(base, toCompare, Double.class)) {
                return (Double) base.getValue() <= (Double) toCompare.getValue();
            } else {
                throw new IllegalArgumentException("Column Types not allowed!");
            }
        }
    },

    EQUALS("=", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areSameType(base, toCompare)) {
                return base.getValue().hashCode() == toCompare.getValue().hashCode();
            }
            throw new IllegalArgumentException("Expect same Type: got different");
        }
    },

    LIKE("like", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> toCompare, Column<?> pattern) {
            if (OperatorValuesValidator.areSameType(toCompare, pattern) &&
                OperatorValuesValidator.areStrings(toCompare, pattern)) {

                return ((String) toCompare.getValue()).matches((String) pattern.getValue());
            }
            throw new IllegalArgumentException("Expect same Type or Boolean Type: got different");
        }
    },

    AND("and", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areBooleans(base, toCompare)) {
                return (Boolean) base.getValue() && (Boolean) toCompare.getValue();
            }
            throw new IllegalArgumentException("Expect same Type or Boolean Type: got different");
        }
    },

    OR("or", EnumSet.of(Command.WHERE)) {
        public boolean compare(Column<?> base, Column<?> toCompare) {
            if (OperatorValuesValidator.areBooleans(base, toCompare)) {
                return (Boolean) base.getValue() || (Boolean) toCompare.getValue();
            }
            throw new IllegalArgumentException("Expect same Type or Boolean Type: got different");
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
