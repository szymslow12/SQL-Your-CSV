package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ColumnServiceTest {

    private ColumnService service;

    @BeforeEach
    private void initializaPrivateFields() {
        service = new ColumnService();
    }


    @Test
    void shouldAddColumn() {
        int expected = 1;
        int actual = service.addColumn(new Column<>("value", "name"), new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddColumnAddProperColumn() {
        Column<String> expected = new Column<>("value", "name");
        Column<?> actual = service.addColumn(expected, new ArrayList<>()).get(0);

        assertEquals(expected, actual);
    }
}