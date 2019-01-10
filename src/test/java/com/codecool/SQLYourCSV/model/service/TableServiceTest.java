package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.Data;
import com.codecool.SQLYourCSV.model.query2.Query;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

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
        when(query.getColumns()).thenReturn(new String[]{"column_one", "column_two", "column_three"});
        when(query.getClauseName()).thenReturn("WHERE");
        when(query.getStatement()).thenReturn("SELECT");
        when(data.getSingleData(anyString())).thenReturn();
    }


    private List<String[]> createTestTableData() {

    }

}