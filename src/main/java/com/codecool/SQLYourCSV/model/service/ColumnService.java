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
}
