package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RowServiceTest {

    private RowService service;

    @Mock
    private ColumnService columnService;


    @BeforeAll
    private void initializePrivateFields() {
        MockitoAnnotations.initMocks(this);
        service = new RowService();
    }


    private Row[] generateRowArray(int size, boolean isWithPrimaryKey) {
        if (isWithPrimaryKey) {

            return IntStream.range(0, size).mapToObj(
                i -> new Row(
                    columnService,
                    new Column<>("value" + i, "name" + i)
                )
            ).toArray(Row[]::new);
        }
        return IntStream.range(0, size).mapToObj(
            i -> new Row(columnService)).toArray(Row[]::new);
    }


    @Test
    void shouldAddRow() {
        Row toAdd = new Row(columnService);

        int expected = 1;
        int actual = service.addRow(toAdd, new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddRowThrowExceptionWhenAttemptToAddNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addRow(null, new ArrayList<>()));
    }


    @Test
    void shouldAddRowThrowExceptionWhenRowListIsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addRow(new Row(columnService), null));
    }


    @Test
    void shouldAddRows() {
        int expected = 10;
        int actual = service.addRows(generateRowArray(10, false), new ArrayList<>()).size();

        assertEquals(expected, actual);
    }
}