package com.codecool.SQLYourCSV.model.datapresentation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {

    @Test
    void shouldEquals_OneColumn() {
        Column<String> column1 = new Column<>("value", "name");

        assertTrue(column1.equals(column1));
    }


    @Test
    void shouldEquals_TwoColumns() {
        Column<String> column1 = new Column<>("value", "name");
        Column<String> column2 = new Column<>("value", "name");

        assertTrue(column1.equals(column2));
    }


    @Test
    void shouldNotEquals_OneColumnAndNull() {
        Column<String> column1 = new Column<>("value", "name");

        assertFalse(column1.equals(null));
    }


    @Test
    void shouldNotEquals_TwoColumns() {
        Column<String> column1 = new Column<>("value", "name");
        Column<String> column2 = new Column<>("value1", "name1");

        assertFalse(column1.equals(column2));
    }


    @Test
    void shouldEquals_Symmetric() {
        Column<String> column1 = new Column<>("value", "name");
        Column<String> column2 = new Column<>("value", "name");

        assertTrue(column1.equals(column2) && column2.equals(column1));
        assertEquals(column1.hashCode(), column2.hashCode());
    }


    @Test
    void shouldHashCodeBeTheSameOnSimilarObjects() {
        Column<String> column1 = new Column<>("value", "name");
        Column<String> column2 = new Column<>("value", "name");

        assertNotSame(column1, column2);
        assertEquals(column1.hashCode(), column2.hashCode());
    }


    @Test
    void shouldHashCodeBeDifferentOnDifferentObjects() {
        Column<String> column1 = new Column<>("value1", "name1");
        Column<String> column2 = new Column<>("value2", "name2");

        assertNotSame(column1, column2);
        assertNotEquals(column1.hashCode(), column2.hashCode());
    }
}