package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.CSVData;
import com.codecool.SQLYourCSV.model.data.Data;
import com.codecool.SQLYourCSV.model.datastructure.Column;
import com.codecool.SQLYourCSV.model.datastructure.Row;
import com.codecool.SQLYourCSV.model.datastructure.Table;
import com.codecool.SQLYourCSV.model.enumeration.Command;
import com.codecool.SQLYourCSV.model.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class TableService {

    @Autowired
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }


    public Data getData() {
        return data;
    }


    public Table createTableFromFile(String filename) {
//        loadData(filename);

        List<String[]> dataFromCSV = data.getSingleData(filename);
        String[] columnsNames = dataFromCSV.get(0);

        Table table = createTable(filename, columnsNames);

        addTableRows(table, dataFromCSV, columnsNames);

        return table;
    }


    public Table createTableFromQuery(Query query ) {
        Table table = new Table();
        table.setService(new RowService());
        Command[] commands = query.getCommands();
        Stream.of(commands).forEach(command -> {
            if (command.getName().equalsIgnoreCase("SELECT")) {
                String[] columnsNames = (String[]) command.selector().getValue();
                Row headers = new Row(new ColumnService());
                Function<String, Column<String>> mapToColumn = colName -> new Column<>(colName, colName);
                headers.setColumns(Stream.of(columnsNames).map(mapToColumn).collect(Collectors.toList()));
                table.setHeaders(headers);
            } else if (command.getName().equalsIgnoreCase("FROM")) {
                String tableName = (String) command.selector().getValue();
                table.setName(tableName);
            }
        });
        table.setRows(new LinkedList<>());
        return table;
    }


//    private void loadData(String filename) {
//        if (data.getSingleData(filename) == null && data.getClass().getSimpleName().equals("CSVData")) {
//            ((CSVData) data).loadFromFile(filename);
//        }
//    }


    private Table createTable(String filename, String[] columnsNames) {
        return new Table(createHeader(columnsNames), new RowService(),
            filename.substring(0, filename.indexOf(".")));
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


    private void addTableRows(Table table, List<String[]> dataFromCSV, String[] columnsNames) {
        IntStream.range(1, dataFromCSV.size()).forEach(
            i -> table.setRows(
                table.getService().addRow(
                    createRow(dataFromCSV.get(i), columnsNames),
                    table.getRows()
                )
            )
        );
    }


    private Row createRow(String[] rowValues, String[] columnsName) {
        Row row = new Row(new ColumnService(), new Column<>(rowValues[0], columnsName[0]));

        IntStream.range(1, columnsName.length).forEach(
            i -> row.setColumns(
                row.getService().addColumn(
                    new Column<>(checkAndCastIfIsNumber(rowValues[i]), columnsName[i]), row.getColumns()
                )
            )
        );

        return row;
    }


    private Object checkAndCastIfIsNumber(String toCheck) {
        if (NumberUtils.isNumber(toCheck)) {
            try {
                return NumberFormat.getInstance().parse(toCheck);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return toCheck;
    }
}
