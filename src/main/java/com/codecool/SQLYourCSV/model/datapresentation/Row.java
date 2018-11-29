package com.codecool.SQLYourCSV.model.datapresentation;

import com.codecool.SQLYourCSV.model.service.ColumnService;

import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<Column<?>> columns;
    private Column<?> primaryKey;
    private ColumnService service;

    public Row(ColumnService service, Column<?> primaryKey) {
        this.service = service;
        this.primaryKey = primaryKey;
        columns = new ArrayList<>();
    }


//    public void setPrimaryKey(Column<?> primaryKey) {
//        this.primaryKey = primaryKey;
//    }


    public Column<?> getPrimaryKey() {
        return primaryKey;
    }


//    public void setService(ColumnService service) {
//        this.service = service;
//    }


    public ColumnService getService() {
        return service;
    }


    public List<Column<?>> getColumns() {
        return columns;
    }


    public void setColumns(List<Column<?>> columns) {
        this.columns = columns;
    }


    public int size() {
        return columns.size();
    }


    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder("|");

        columns.stream().forEach(
            column -> sB.append(
                String.format(" name = %s, value = %s |",column.getName(), column.getValue())
            )
        );
        return sB.toString();
    }
}
