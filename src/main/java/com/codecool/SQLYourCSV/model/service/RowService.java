package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import com.codecool.SQLYourCSV.model.datastructure.Row;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class RowService {

    public List<Row> addRow(Row toAdd, List<Row> rows) {
        validateRowList(rows).add(validateRow(toAdd));
        return rows;
    }


    public List<Row> addRows(Row[] toAdd, List<Row> rows) {
        if (toAdd == null) throw new IllegalArgumentException("Expect Row[]: got null");
        Stream.of(toAdd).forEach(row -> validateRowList(rows).add(validateRow(row)));
        return rows;
    }


    public Row getRowByIndex(int index, List<Row> rows) {
        return validateRowList(rows).get(validateIndex(index, rows.size()) - 1);
    }


    public Row getRowByPrimaryKey(Column<?> primaryKey, List<Row> rows) {
        if (primaryKey == null) throw new IllegalArgumentException("Expect Column<?>: got null");

        Optional<Row> rowToFind = validateRowList(rows).stream().filter(
                row -> row.getPrimaryKey().equals(primaryKey)
        ).findFirst();

        return validateAndGetRowFromOptional(rowToFind, primaryKey.getName(), primaryKey.getValue().toString());
    }


    public Row getRowByColumnValue(Column<?> toFind, List<Row> rows) {
        if (toFind == null) throw new IllegalArgumentException("Expect Column<?>: got null");

        Optional<Row> rowToFind = validateRowList(rows).stream().filter(
                row -> row.getColumns().stream().anyMatch(
                        column -> column.equals(toFind)
                )
        ).findFirst();

        return validateAndGetRowFromOptional(rowToFind, toFind.getName(), toFind.getValue().toString());
    }


    private Row validateRow(Row toValid) {
        if (toValid != null) {
            return toValid;
        }
        throw new IllegalArgumentException("Expect Row: got null");
    }


    private int validateIndex(int index, int rowListSize) {
        if (index == 0 || index > rowListSize) {
            throw new IllegalArgumentException(
                String.format("Row with index = '%s' does not exist!", index)
            );
        }
        return index;
    }


    private List<Row> validateRowList(List<Row> toValid) {
        if (toValid != null) {
            return toValid;
        }
        throw new IllegalArgumentException("Expect List<Row>: got null");
    }


    private Row validateAndGetRowFromOptional(Optional<Row> toValid, String name, String value) {
        if (toValid.isPresent()) {
            return toValid.get();
        }
        return null;
    }
}
