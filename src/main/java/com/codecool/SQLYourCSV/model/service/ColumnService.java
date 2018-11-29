package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;

import java.util.List;

public class ColumnService {

    public List<Column<?>> addColumn(Column<?> toAdd, List<Column<?>> container) {
        container.add(toAdd);
        return container;
    }


    public List<Column<?>> addColumns(Column<?>[] toAdd, List<Column<?>> container) {
        for (int i = 0; i < toAdd.length; i++) {
            container = addColumn(toAdd[i], container);
        }
        return container;
    }

    //update methods in uml
    public Object getValueByName(String name, List<Column<?>> columns) {
        return columns.stream().filter(column -> column.getName().equalsIgnoreCase(name)).findFirst().get().getValue();
    }


    public Object getValueByIndex(int index, List<Column<?>> columns) {
        return columns.get(index);
    }
}
