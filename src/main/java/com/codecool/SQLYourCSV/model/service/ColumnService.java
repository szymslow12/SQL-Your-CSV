package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;

import java.util.List;
import java.util.stream.Stream;

public class ColumnService {

    public List<Column<?>> addColumn(Column<?> toAdd, List<Column<?>> container) {
        validateColumnList(container).add(validateColumn(toAdd));
        return container;
    }


    public List<Column<?>> addColumns(Column<?>[] toAdd, List<Column<?>> container) {
        Stream.of(toAdd).forEach(
            column -> validateColumnList(container).add(validateColumn(column))
        );
        return container;
    }


    public Object getValueByName(String name, List<Column<?>> columns) {
        return validateColumnList(columns).stream().filter(column -> column.getName().equalsIgnoreCase(validateName(name))).findFirst().get().getValue();
    }


    public Object getValueByIndex(int index, List<Column<?>> columns) {
        return validateColumnList(columns).get(validateIndex(index, columns.size()) - 1).getValue();
    }


    public Column<?> getColumnByName(String name, List<Column<?>> columns) {
        return validateColumnList(columns).stream().filter(column -> column.getName().equalsIgnoreCase(validateName(name))).findFirst().get();
    }


    public Column<?> getColumnByIndex(int index, List<Column<?>> columns) {
        return validateColumnList(columns).get(validateIndex(index, columns.size()) - 1);
    }


    private Column<?> validateColumn(Column<?> toValid) {
        if (toValid != null) {
            return toValid;
        }
        throw new IllegalArgumentException("Expect Column: got null");
    }


    private List<Column<?>> validateColumnList(List<Column<?>> toValid) {
        if (toValid != null) {
            return toValid;
        }
        throw new IllegalArgumentException("Expect List<Column>: got null");
    }


    private int validateIndex(int index, int columnsNumber) {
        if (index == 0 || index > columnsNumber) {
            throw new IllegalArgumentException(String.format("Column with index = %s does not exist!", index));
        }
        return index;
    }


    private String validateName(String name) {
        if (name != null) {
            return name;
        }
        throw new IllegalArgumentException("Expect String: got null");
    }
}
