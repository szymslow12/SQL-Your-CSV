package com.codecool.SQLYourCSV.model.service;

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
}
