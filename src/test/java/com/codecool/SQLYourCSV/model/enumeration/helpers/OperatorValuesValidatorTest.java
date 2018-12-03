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
}