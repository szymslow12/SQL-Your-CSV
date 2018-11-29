package com.codecool.SQLYourCSV.model.datapresentation;

import com.codecool.SQLYourCSV.model.service.RowService;

import java.util.List;

public class Table {

    private Row headers;
    private List<Row> rows;
    private RowService service;
    private String name;

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
}
