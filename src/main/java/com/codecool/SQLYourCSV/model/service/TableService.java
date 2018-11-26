package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.CSVData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Table getTableByName(String name) {
        return null;
    }


    public Table createTableFromFile(String filename) {
        return null;
    }


    public Table createTableFromQuery(Query query ) {
        return null;
    }
}
