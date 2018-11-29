package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;

import java.util.List;
import java.util.stream.Stream;

public class RowService {

    public List<Row> addRow(Row toAdd, List<Row> rows) {
        rows.add(toAdd);
        return rows;
    }


    public List<Row> addRows(Row[] toAdd, List<Row> rows) {
        Stream.of(toAdd).forEach(row -> rows.add(row));
        return rows;
    }


    public Row getRowByIndex(int index, List<Row> rows) {
        return rows.get(index);
    }


    public Row getRowByPrimaryKey(Column<?> primaryKey, List<Row> rows) {
        return rows.stream().filter(
            row -> row.getPrimaryKey().getValue() == primaryKey.getValue()
        ).findFirst().get();
    }


    public Row getRowByColumnValue(Column<?> toFind, List<Row> rows) {
        return rows.stream().filter(
            row -> row.getColumns().stream().anyMatch(
                column -> findColumnToFind(column, toFind)
            )
        ).findFirst().get();
    }


    private boolean findColumnToFind(Column<?> column, Column<?> toFind) {
        return column.getName() == toFind.getName() & column.getValue() == toFind.getValue();
    }
}
