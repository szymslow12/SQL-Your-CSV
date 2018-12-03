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

        assertTrue(OperatorValuesValidator.areNumbers(toTest, toTest));
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


    @Test
    void testAreStrings_StringsCase() {
        Column<String> toTest = new Column<>("value", "name");

        assertTrue(OperatorValuesValidator.areStrings(toTest, toTest));
    }


    @Test
    void testAreStrings_OneStringOneDifferent() {
        Column<Integer> integerColumn = new Column<>(1, "name");
        Column<String> stringColumn = new Column<>("value", "name");

        assertFalse(OperatorValuesValidator.areStrings(integerColumn, stringColumn));
    }


    @Test
    void testAreStrings_NotStrings() {
        Column<Integer> toTest1 = new Column<>(1, "name");
        Column<Integer> toTest2 = new Column<>(1, "name");

        assertFalse(OperatorValuesValidator.areStrings(toTest1, toTest2));
    }


    @Test
    void testAreStringsThrowsExceptionWhenNullsIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> OperatorValuesValidator.areStrings(null, null));
    }


    @Test
    void testAreBooleans_BooleanCase() {
        Column<Boolean> toTest = new Column<>(true, "name");

        assertTrue(OperatorValuesValidator.areBooleans(toTest, toTest));
    }


    @Test
    void testAreBooleans_OneBooleanOneDifferent() {
        Column<Integer> integerColumn = new Column<>(1, "name");
        Column<Boolean> booleanColumn = new Column<>(false, "name");

        assertFalse(OperatorValuesValidator.areBooleans(integerColumn, booleanColumn));
    }


    @Test
    void testAreBooleans_NotBooleans() {
        Column<String> toTest1 = new Column<>("value", "name");
        Column<String> toTest2 = new Column<>("value", "name");

        assertFalse(OperatorValuesValidator.areBooleans(toTest1, toTest2));
    }


    @Test
    void testAreBooleansThrowsExceptionWhenNullsIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> OperatorValuesValidator.areBooleans(null, null));
    }
}