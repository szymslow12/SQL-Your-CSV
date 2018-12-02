package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RowServiceTest {

    private RowService service;

    @Mock
    private ColumnService columnService;


    @BeforeEach
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


    private List<Row> generateRowList(int size, boolean isWithPrimaryKey) {
        return Stream.of(generateRowArray(size, isWithPrimaryKey)
            ).collect(Collectors.toList());
    }


    @Test
    void shouldAddRow() {
        Row toAdd = new Row(columnService);

        int expected = 1;
        int actual = service.addRow(toAdd, new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddRowAddProperRow() {
        Row expected = new Row(columnService);
        Row actual = service.addRow(expected, new ArrayList<>()).get(0);

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
        int actual = service.addRows(generateRowArray(10, false),
            new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddRowsAddProperRows() {
        Row[] expected = generateRowArray(10, false);
        Row[] actual = service.addRows(expected,
            new ArrayList<>()).stream().toArray(Row[]::new);

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldAddRowsThrowExceptionWhenNullPassedInsteadRowArray() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addRows(null, new ArrayList<>()));
    }


    @Test
    void shouldAddRowsThrowExceptionWhenAttemptToAddRowToNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addRows(generateRowArray(10, false), null));
    }


    @Test
    void shouldAddRowsThrowExceptionWhenAttemptToAddNull() {
        Row[] toAdd = new Row[] {new Row(columnService), null};

        assertThrows(IllegalArgumentException.class,
            () -> service.addRows(toAdd, new ArrayList<>()));
    }


    @Test
    void shouldGetRowByIndexReturnProperRow() {
        Row expected = new Row(columnService);
        Row actual = service.getRowByIndex(1, new ArrayList<Row>() {
            {
                add(expected);
            }
        });

        assertEquals(expected, actual);
    }


    @Test
    void shouldGetRowByIndexThrowExceptionWhenIndexIsZero() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getRowByIndex(0, ));
    }


    @Test
    void shouldGetRowByIndexThrowExceptionWhenIndexBiggerThanActualRowNumber() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getRowByIndex(11, ));
    }
}