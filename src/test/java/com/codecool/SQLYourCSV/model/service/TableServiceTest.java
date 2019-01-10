package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.Data;
import com.codecool.SQLYourCSV.model.datastructure.Row;
import com.codecool.SQLYourCSV.model.datastructure.Table;
import com.codecool.SQLYourCSV.model.query2.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TableServiceTest {

    @Mock
    private Data data;

    @Mock
    private Query query;

    private TableService tableService;


    @BeforeAll
    private void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    private List<String[]> createTestTableData(int rowsNumber, int columnsNumber) {
        IntFunction<String[]> createTableData = i -> {
            String[] rowsValues = new String[columnsNumber];
            if (i == 0) {
                IntConsumer createHeaders = j -> rowsValues[j] = String.format("column_%s", j);
                IntStream.range(0, columnsNumber).forEach(createHeaders);
                return rowsValues;
            } else {
                IntConsumer createRows = j -> rowsValues[j] = String.format("value_%s", j);
                IntStream.range(0, columnsNumber).forEach(createRows);
                return rowsValues;
            }
        };

        return IntStream.range(0, rowsNumber).mapToObj(createTableData).collect(Collectors.toList());
    }

    @BeforeEach
    private void initPrivateFieldsAndSetStubs() {
        this.tableService = new TableService();
        tableService.setData(data);
        when(query.getTableName()).thenReturn("tableName");
        when(query.getColumns()).thenReturn(new String[]{"column_1", "column_2", "column_3"});
        when(query.getClauseName()).thenReturn("WHERE");
        when(query.getStatement()).thenReturn("SELECT");
        when(data.getSingleData(anyString())).thenReturn(createTestTableData(5, 5));
    }


    @Test
    void shouldCreateTableFromFileCreateTableWhenTableNameIsGiven() {
        assertNotNull(tableService.createTableFromFile("tableName.csv"));
    }


    @Test
    void shouldCreateTableFromFileThrowExceptionWhenFileNameIsNotProper() {
        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromFile("bad-table-name"));
    }


    @Test
    void shouldCreateTableFromFileThrowExceptionWhenFileNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromFile(null));
    }


    @Test
    void shouldCreateTableFromFileReturnTableWithProperNumberOfRowsExcludingHeaderRow() {
        Table toTest = tableService.createTableFromFile("tableName.csv");

        int expected = 4;
        int actual = toTest.size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldCreateTableFromFileReturnTableWithProperNumberOfRowsIncludingHeaderRow() {
        Table toTest = tableService.createTableFromFile("tableName.csv");

        int expected = 5;
        int actual = toTest.size() + (toTest.getHeaders() != null ? 1: 0);

        assertEquals(expected, actual);
    }


    @Test
    void shouldCreateTableFromFileReturnTableWithProperNumberOfColumnsInRows() {
        Table toTest = tableService.createTableFromFile("tableName.csv");

        int expected = 5;

        IntConsumer testNumberOfColumns = i -> {
            int actual;
            if (i == 0) {
                actual = toTest.getHeaders().size();
                assertEquals(expected, actual);
            } else {
//                minus one because index of rows starts from 0 but index 0 is used to test header
//                so index 1 and bigger is used to test columns in rows so that why there is minus one
                actual = toTest.getRows().get(i - 1).size();
                assertEquals(expected, actual);
            }
        };

        IntStream.range(0, 5).forEach(testNumberOfColumns);
    }


    @Test
    void shouldCreateTableFromFileThrowExceptionWhenPassedDataIsEmpty() {
        when(data.getSingleData(anyString())).thenReturn(createTestTableData(0, 0));

        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromFile("tableName.csv"));
    }


    @Test
    void shouldCreateTableFromFileThrowExceptionWhenPassedDataIsNull() {
        when(data.getSingleData(anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromFile("tableName.csv"));
    }


    @Test
    void shouldCreateTableFromFileCreateTableWhenOnlyColumnsNamesArePassed() {
        when(data.getSingleData(anyString())).thenReturn(createTestTableData(1, 5));

        assertNotNull(tableService.createTableFromFile("tableName.csv").getHeaders());
    }


    @Test
    void shouldCreateTableFromQueryReturnTable() {
        assertNotNull(tableService.createTableFromQuery(query));
    }


    @Test
    void shouldCreateTableFromQueryThrowExceptionWhenNullPassed() {
        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromQuery(null));
    }


    @Test
    void shouldCreateTableFromQueryReturnTableWithHeader() {
        assertNotNull(tableService.createTableFromQuery(query).getHeaders());
    }


    @Test
    void shouldCreateTableFromQueryThrowExceptionWhenDataFromGivenTableIsNull() {
        when(query.getTableName()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromQuery(query));
    }


    @Test
    void shouldCreateTableFromQueryThrowExceptionWhenDataFromGivenTableIsEmpty() {
        when(data.getSingleData(anyString())).thenReturn(createTestTableData(0, 0));

        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromQuery(query));
    }


    @Test
    void shouldCreateTableFromQueryReturnTableWithProperColumnsNumberInHeader() {
        int expected = 3;
        int actual = tableService.createTableFromQuery(query).getHeaders().size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldCreateTableFromQueryThrowExceptionWhenColumnsInQueryDoesNotExistInTable() {
        when(query.getColumns()).thenReturn(new String[] {"not_exist", "not_exist2"});

        assertThrows(IllegalArgumentException.class, () -> tableService.createTableFromQuery(query));
    }


    @Test
    void shouldCreateTableFromQueryReturnTableWithAllRowsExcludingHeader() {
        int expected = 4;
        int actual = tableService.createTableFromQuery(query).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldCreateTableFromQueryReturnTableWithProperNumberOfColumnsInEachRow() {
        Table toTest = tableService.createTableFromQuery(query);
        int expected = 3;

        IntStream.range(0, toTest.size()).forEach(i -> {
            int actual = toTest.getRows().get(i).size();
            assertEquals(expected, actual);
        });
    }


    @Test
    void shouldCreateTableFromQueryReturnTableWithProperColumnValues() {
        Table toTest = tableService.createTableFromQuery(query);

        IntConsumer getRows = i -> IntStream.range(0, toTest.getRows().get(i).size()).
            forEach(j -> {
                Row row = toTest.getRows().get(i);
                String expected = String.format("value_%s", j + 1);
                String actual = getValueFromEachColumn(j, row);

                assertTrue(actual.equals(expected));
            });
        IntStream.range(0, toTest.size()).forEach(getRows);
    }


    private String getValueFromEachColumn(int index, Row row) {
        return (String) row.getColumns().get(index).getValue();
    }
}