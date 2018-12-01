package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RowServiceTest {

    private RowService service;

    @Mock
    private ColumnService columnService;

    private Column<String> primaryKey;

    @BeforeAll
    private void initializePrivateFields() {
        MockitoAnnotations.initMocks(this);
        service = new RowService();
    }


    @Test
    void shouldAddRow() {
        Row toAdd = new Row(columnService);

        int expected = 1;
        int actual = service.addRow(toAdd, new ArrayList<>()).size();

        assertEquals(expected, actual);
    }
}