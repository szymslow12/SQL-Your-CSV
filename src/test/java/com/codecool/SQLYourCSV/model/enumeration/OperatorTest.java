package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {


    private boolean equals(Column<?> first, Column<?> second) {
        return Operator.EQUALS.compare(first, second);
    }


    private boolean biggerThan(Column<?> first, Column<?> second) {
        return Operator.BIGGER_THAN.compare(first, second);
    }


    private boolean smallerThan(Column<?> first, Column<?> second) {
        return Operator.SMALLER_THAN.compare(first, second);
    }


    private boolean biggerThanOrEquals(Column<?> first, Column<?> second) {
        return Operator.BIGGER_THAN_OR_EQULAS.compare(first, second);
    }


    private boolean like(Column<?> first, Column<?> second) {
        return Operator.LIKE.compare(first, second);
    }


    private boolean and(Column<?> first, Column<?> second) {
        return Operator.AND.compare(first, second);
    }


    private boolean or(Column<?> first, Column<?> second) {
        return Operator.OR.compare(first, second);
    }


    @Test
    void shouldEQUALS_CompareProperlySameColumn_OneReference() {
        Column<String> toCompare = new Column<>("value", "name");

        assertTrue(equals(toCompare, toCompare));
    }


    @Test
    void shouldEQUALS_CompareProperlySameColumnValue_TwoReferences() {
        Column<String> first = new Column<>("value", "name");
        Column<String> second = new Column<>("value", "name");

        assertTrue(equals(first, second));
    }


    @Test
    void shouldEQUALS_CompareProperlyNotSameColumnValue() {
        Column<String> first = new Column<>("first", "first");
        Column<String> second = new Column<>("second", "second");

        assertFalse(equals(first, second));
    }


    @Test
    void shouldEQUALS_ThrowExceptionWhenAttemptToCompareDifferentTypes() {
        Column<String> stringColumn = new Column<>("string", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertThrows(IllegalArgumentException.class,
            () -> equals(stringColumn, integerColumn));
    }


    @Test
    void shouldEQUALS_ThrowExceptionWhenNullIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> equals(null, null));
    }


    @Test
    void shouldBIGGER_THAN_CompareProperly_EqualValues() {
        Column<Integer> toCompare = new Column<>(1, "name");

        assertFalse(biggerThan(toCompare, toCompare));
    }


    @Test
    void shouldBIGGER_THAN_CompareProperly_BiggerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertTrue(biggerThan(bigger, smaller));
    }


    @Test
    void shouldBIGGER_THAN_CompareProperly_SmallerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertFalse(biggerThan(smaller, bigger));
    }


    @Test
    void shouldBIGGER_THAN_ThrowExceptionWhenAttemptToCompareDifferentTypes() {
        Column<String> stringColumn = new Column<>("string", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertThrows(IllegalArgumentException.class,
            () -> biggerThan(stringColumn, integerColumn));
    }


    @Test
    void shouldBIGGER_THAN_ThrowExceptionWhenNullIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> biggerThan(null, null));
    }


    @Test
    void shouldSMALLER_THAN_CompareProperly_EqualValues() {
        Column<Integer> toCompare = new Column<>(1, "name");

        assertFalse(smallerThan(toCompare, toCompare));
    }


    @Test
    void shouldSMALLER_THAN_CompareProperly_BiggerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertFalse(smallerThan(bigger, smaller));
    }


    @Test
    void shouldSMALLER_THAN_CompareProperly_SmallerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertTrue(smallerThan(smaller, bigger));
    }


    @Test
    void shouldSMALLER_THAN_ThrowExceptionWhenAttemptToCompareDifferentTypes() {
        Column<String> stringColumn = new Column<>("string", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertThrows(IllegalArgumentException.class,
            () -> smallerThan(stringColumn, integerColumn));
    }


    @Test
    void shouldSMALLER_THAN_ThrowExceptionWhenNullIsPassed() {
        assertThrows(IllegalArgumentException.class,
            () -> smallerThan(null, null));
    }


    @Test
    void shouldBIGGER_THAN_OR_EQUAL_CompareProperly_EqualValues() {
        Column<Integer> toCompare = new Column<>(1, "name");

        assertTrue(biggerThanOrEquals(toCompare, toCompare));
    }


    @Test
    void shouldBIGGER_THAN_OR_EQUAL_CompareProperly_BiggerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertTrue(biggerThanOrEquals(bigger, smaller));
    }


    @Test
    void shouldBIGGER_THAN_OR_EQUAL_CompareProperly_SmallerValue() {
        Column<Integer> bigger = new Column<>(2, "name");
        Column<Integer> smaller = new Column<>(1, "name");

        assertFalse(biggerThanOrEquals(smaller, bigger));
    }


    @Test
    void shouldBIGGER_THAN_OR_EQUAL_ThrowExceptionWhenAttemptToCompareDifferentTypes() {
        Column<String> stringColumn = new Column<>("string", "name");
        Column<Integer> integerColumn = new Column<>(1, "name");

        assertThrows(IllegalArgumentException.class,
            () -> biggerThanOrEquals(stringColumn, integerColumn));
    }


    @Test
    void shouldBIGGER_THAN_OR_EQUAL_ThrowExceptionWhenNullIsPassed() {
        assertThrows(IllegalArgumentException.class,
                () -> biggerThanOrEquals(null, null));
    }
}