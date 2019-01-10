package com.codecool.SQLYourCSV.model.data;

import com.codecool.SQLYourCSV.model.service.SheetsServiceUtil;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SheetData implements Data {
    private static final String SPREADSHEET_ID = "1dBTlx7Xuslbfj7bdkIu6r-op-1dOiMDW7fplnjt9EiA";

    private Map<String, List<String[]>> data;
    private Sheets sheetsService;


    public SheetData() throws IOException, GeneralSecurityException {
        data = new HashMap<>();
        sheetsService = SheetsServiceUtil.getSheetsService();
    }

    @Override
    public List<String[]> getSingleData(String dataName) {
        return data.get(dataName);
    }

    @Override
    public Map<String, List<String[]>> getAllData() {
        return data;
    }

    public void loadFromFile(String name) throws IOException {
        data.put(name, SheetReader.readSheet(sheetsService, SPREADSHEET_ID, name));
    }

    public void loadFromMultipleFiles(String... names) {
        SheetReader.readSheets(sheetsService, SPREADSHEET_ID, names);
    }
}
