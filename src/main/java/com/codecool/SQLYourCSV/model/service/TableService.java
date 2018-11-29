package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.CSVData;
import com.codecool.SQLYourCSV.model.datapresentation.Column;
import com.codecool.SQLYourCSV.model.datapresentation.Row;
import com.codecool.SQLYourCSV.model.datapresentation.Table;
import com.codecool.SQLYourCSV.model.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

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

//Update uml to not have this method
//    public Table getTableByName(String name) {
//        return null;
//    }


    public Table createTableFromFile(String filename) {
        if (data.getCSVFileData(filename) == null) {
            data.loadFromFile(filename);
        }
        List<String[]> dataFromCSV = data.getCSVFileData(filename);
        Table table = new Table(createHeader(dataFromCSV.get(0)), new RowService(), filename.replace(".csv", ""));

        return null;
    }


    private Row createHeader(String[] headerNames) {
        String primaryKey = headerNames[0];
        Row header = new Row(new ColumnService(), new Column<String>(primaryKey, primaryKey));
        Stream.of(headerNames.).forEach(name -> addColumnToHeader(name, header));
        return header;
    }


    private void addColumnToHeader(String name, Row header) {
        header.setColumns(header.getService().addColumn(new Column<>(name, name), header.getColumns()));
    }


    public Table createTableFromQuery(Query query ) {
        return null;
    }
}
