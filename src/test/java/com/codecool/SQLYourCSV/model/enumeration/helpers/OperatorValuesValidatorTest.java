package com.codecool.SQLYourCSV.model.enumeration.helpers;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorValuesValidatorTest {

    @Test
    void testAreSameType_SameTypes() {
        Column<String> toTest = new Column<>("value", "name");

        assertTrue(OperatorValuesValidator.areSameType(toTest, toTest));
    }


    @Test
    void testAreSameType_NotSameTypes() {
        Column<String> stringColumn = new Column<>("value", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertFalse(OperatorValuesValidator.areSameType(stringColumn, integerColumn));
    }


    @Test
    void testAreSameTypeThrowsExceptionWhenNullsArePassed() {
        assertThrows(IllegalArgumentException.class,
            () -> OperatorValuesValidator.areSameType(null, null));
    }


    @Test
    void testAreNumbers_NumbersCase() {
        Column<Integer> toTest = new Column<>(1, "name");

        assertTrue(OperatorValuesValidator.areNumbers());
    }


    @Test
    void testAreNumbers_OneNumberOneDifferent() {
        Column<Integer> integerColumn = new Column<>(1, "name");
        Column<String> stringColumn = new Column<>("value", "name");

        assertFalse(OperatorValuesValidator.areNumbers(integerColumn, stringColumn));
    }


    @Test
    void testAreNumbers_NotNumbers() {
        Column<String> toTest1 = new Column<>("value", "name");
        Column<String> toTest2 = new Column<>("value", "name");

        assertFalse(OperatorValuesValidator.areNumbers(toTest1, toTest2));
    }


    @Test
    void testAreNumbersThrowsExceptionWhenNullsIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> OperatorValuesValidator.areNumbers(null, null));
    }
}