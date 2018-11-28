package com.codecool.SQLYourCSV.model.data;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CSVData {
    private Map<String, List<String[]>> data;

    public Map<String, List<String[]>> getData() {
        return data;
    }


    public CSVData() {
        data = new HashMap<>();
    }


    public void loadFromFile(String file) {
        try {
            data.put(file, FileReader.readFile(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFromMultipleFiles(String... files) {
        data = FileReader.readFiles(files);
    }
}
