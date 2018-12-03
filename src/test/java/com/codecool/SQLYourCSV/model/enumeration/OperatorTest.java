package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {


    private boolean compare(Column<?> first, Column<?> second) {
        return Operator.EQUALS.compare(first, second);
    }


    @Test
    void shouldEQUALS_CompareProperlySameColumn_OneReference() {
        Column<String> toCompare = new Column<>("value", "name");

        assertTrue(compare(toCompare, toCompare));
    }


    @Test
    void shouldEQUALS_CompareProperlySameColumn_TwoReferences() {
        Column<String> first = new Column<>("value", "name");
        Column<String> second = new Column<>("value", "name");

        assertTrue(compare(first, second));
    }


    @Test
    void shouldEQUALS_CompareProperlyNotSameColumn() {
        Column<String> first = new Column<>("first", "first");
        Column<String> second = new Column<>("second", "second");

        assertFalse(compare(first, second));
    }


    @Test
    void shouldEQUALS_ThrowExceptionWhenAttemptToCompareDifferentTypes() {
        Column<String> stringColumn = new Column<>("string", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertThrows(IllegalArgumentException.class,
            () -> compare(stringColumn, integerColumn));
    }
}