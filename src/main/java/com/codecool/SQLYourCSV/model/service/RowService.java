package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Row;

import java.util.List;

public class RowService {

    public List<Row> addRow(Row toAdd, List<Row> rows) {
        rows.add(toAdd);
        return rows;
    }


    public List<Row> addRows(Row[] toAdd, List<Row> rows) {
        for (int i = 0; i < toAdd.length; i++) {
            rows = addRow(toAdd[i], rows);
        }
        return rows;
    }
}
