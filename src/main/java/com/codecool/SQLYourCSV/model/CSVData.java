package com.codecool.SQLYourCSV.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CSVData {
    private Map<String, List<String[]>> data;

    public Map<String, List<String[]>> getData() {
        return data;
    }


    public void loadFromFile(String file) {

    }


    public void loadFromMultipleFiles(String... files) {

    }
}
