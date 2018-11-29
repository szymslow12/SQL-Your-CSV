package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;

import java.util.List;
import java.util.stream.Stream;

public class ColumnService {

    public List<Column<?>> addColumn(Column<?> toAdd, List<Column<?>> container) {
        container.add(toAdd);
        return container;
    }


    public List<Column<?>> addColumns(Column<?>[] toAdd, List<Column<?>> container) {
        Stream.of(toAdd).forEach(column -> container.add(column));
        return container;
    }

    //update methods in uml
    public Object getValueByName(String name, List<Column<?>> columns) {
        return columns.stream().filter(column -> column.getName().equalsIgnoreCase(name)).findFirst().get().getValue();
    }


    public Object getValueByIndex(int index, List<Column<?>> columns) {
        return columns.get(index).getValue();
    }


    public Column<?> getColumnByName(String name, List<Column<?>> columns) {
        return columns.stream().filter(column -> column.getName().equalsIgnoreCase(name)).findFirst().get();
    }


    public Column<?> getColumnByIndex(int index, List<Column<?>> columns) {
        return columns.get(index);
    }
}
