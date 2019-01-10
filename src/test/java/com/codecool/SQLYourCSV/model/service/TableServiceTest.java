package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.CSVData;
import com.codecool.SQLYourCSV.model.data.Data;
import com.codecool.SQLYourCSV.model.query2.Query;
import org.junit.jupiter.api.BeforeAll;
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

class TableServiceTest {

    @Mock
    private Data data;

    @Mock
    private Query query;

    private TableService tableService;


    @BeforeAll
    private void initMocks() {
        MockitoAnnotations.initMocks(this);
        when(query.getTableName()).thenReturn("tableName");
        when(query.getColumns()).thenReturn(new String[]{"column_1", "column_2", "column_3"});
        when(query.getClauseName()).thenReturn("WHERE");
        when(query.getStatement()).thenReturn("SELECT");
        when(data.getSingleData(anyString())).thenReturn(createTestTableData(5, 5));
        when(data.getClass().getSimpleName()).thenReturn("CSVData");
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

}