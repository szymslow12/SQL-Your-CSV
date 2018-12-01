package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.CSVData;
import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;
import com.codecool.SQLYourCSV.model.datapresentation.Table;
import com.codecool.SQLYourCSV.model.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class TableService {

    @Autowired
    private CSVData data;

    public void setData(CSVData data) {
        this.data = data;
    }


    public CSVData getData() {
        return data;
    }

//refactor needed
    public Table createTableFromFile(String filename) {
        if (data.getSingleFileData(filename) == null) {
            data.loadFromFile(filename);
        }
        List<String[]> dataFromCSV = data.getSingleFileData(filename);
        String[] columnsNames = dataFromCSV.get(0);

        Table table = new Table(
            createHeader(columnsNames),
            new RowService(),
            filename.substring(0, filename.indexOf("."))
        );

        IntStream.range(1, dataFromCSV.size()).forEach(
            i -> table.setRows(
                table.getService().addRow(
                    createRow(dataFromCSV.get(i), columnsNames),
                    table.getRows()
                )
            )
        );

        return table;
    }


    private Row createHeader(String[] headerNames) {
        String primaryKey = headerNames[0];
        Row header = new Row(new ColumnService(), new Column<String>(primaryKey, primaryKey));
        IntStream.range(1, headerNames.length).forEach(i -> addColumnToHeader(headerNames[i], header));
        return header;
    }


    private void addColumnToHeader(String name, Row header) {
        header.setColumns(header.getService().addColumn(new Column<>(name, name), header.getColumns()));
    }


    private Row createRow(String[] rowValues, String[] columnsName) {
        Row row = new Row(new ColumnService(), new Column<>(rowValues[0], columnsName[0]));

        IntStream.range(1, columnsName.length).forEach(
            i -> row.setColumns(
                row.getService().addColumn(
                    new Column<>(rowValues[i], columnsName[i]), row.getColumns()
                )
            )
        );

        return row;
    }


    public Table createTableFromQuery(Query query ) {
        return null;
    }
}
