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






}