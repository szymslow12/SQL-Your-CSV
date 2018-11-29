package com.codecool.SQLYourCSV.model.datapresentation;

import com.codecool.SQLYourCSV.model.service.RowService;

import java.util.LinkedList;
import java.util.List;

public class Table {

    private Row headers;
    private List<Row> rows;
    private RowService service;
    private String name;

    public Table(Row headers, RowService service, String name) {
        this.headers = headers;
        this.service = service;
        this.name = name;
        this.rows = new LinkedList<>();
    }


    public void setHeaders(Row headers) {
        this.headers = headers;
    }


    public Row getHeaders() {
        return headers;
    }


    public void setService(RowService service) {
        this.service = service;
    }


    public RowService getService() {
        return service;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setRows(List<Row> rows) {
        this.rows = rows;
    }


    public List<Row> getRows() {
        return rows;
    }


    public int size() {
        return rows.size();
    }

    //add toString to UML
    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder(headers.toString());
        rows.stream().forEach(row -> sB.append(row.toString()));
        return sB.toString();
    }
}
