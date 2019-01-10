package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.data.CSVData;
import com.codecool.SQLYourCSV.model.data.Data;
import com.codecool.SQLYourCSV.model.datastructure.Column;
import com.codecool.SQLYourCSV.model.datastructure.Row;
import com.codecool.SQLYourCSV.model.datastructure.Table;
import com.codecool.SQLYourCSV.model.enumeration.Command;
import com.codecool.SQLYourCSV.model.query2.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
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
        if (filename == null || !filename.contains("."))
            throw new IllegalArgumentException("Wrong file name or file name is null!");

        List<String[]> dataFromCSV = validateAndGetData(filename);
        String[] columnsNames = dataFromCSV.get(0);

        Table table = createTable(filename, columnsNames);

        addTableRows(table, dataFromCSV, columnsNames);

        return table;
    }


    public Table createTableFromQuery(Query query ) {
        if (query == null) {
            throw new IllegalArgumentException("Query is not given, received null!");
        }
        List<String[]> data = validateAndGetData(query.getTableName());
        String[] columnsNameFromQuery = query.getColumns();
        String[] columnsNameFromData = data.get(0);
        Table table = new Table();
        table.setHeaders(
            createHeader(
                checkAndGetColumnNamesIfExist(columnsNameFromQuery, columnsNameFromData)
            )
        );
        IntFunction<Row> createRowsFromData = i -> {
            Row row = new Row(new ColumnService());

            IntFunction<Column<Object>> createColumn =
                j -> new Column<>(checkAndCastIfIsNumber("value"), columnsNameFromQuery[j]);

            row.setColumns(
                IntStream.range(0, columnsNameFromQuery.length).
                mapToObj(createColumn).
                collect(Collectors.toList())
            );
            return row;
        };

        table.setRows(IntStream.range(0, data.size() - 1).
            mapToObj(createRowsFromData).
            collect(Collectors.toList())
        );
        return table;
    }

    private List<String[]> validateAndGetData(String dataName) {
        List<String[]> toValidate = data.getSingleData(dataName);
        if (toValidate == null || toValidate.size() == 0) {
            throw new IllegalArgumentException("Data from file is empty or data is null!");
        }
        return toValidate;
    }


    private String[] checkAndGetColumnNamesIfExist(String[] columnsFromQuery, String[] columnsFromData) {
        boolean columnsExist = compareColumns(columnsFromQuery, columnsFromData);
        if (columnsExist) {
            return columnsFromQuery;
        }
        throw new IllegalArgumentException("Column not exists in data!");
    }


    private boolean compareColumns(String[] columnsFromQuery, String[] columnsFromData) {
        Predicate<String> checkColumns = columnFromQuery -> {
            Predicate<String> compareNames = columnFromData -> columnFromQuery.equals(columnFromData);
            return Stream.of(columnsFromData).anyMatch(compareNames);
        };

        return Stream.of(columnsFromQuery).allMatch(checkColumns);
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
