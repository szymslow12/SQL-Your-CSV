package com.codecool.SQLYourCSV.model.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SheetReader {
    public static List<String[]> readSheet(Sheets sheetsService, String spreadsheetId, String name) throws IOException {

        ValueRange vr = sheetsService.spreadsheets().values().get(spreadsheetId, name).execute();

        return vr.getValues().stream()
                .map(SheetReader::listToArrayString)
                .collect(Collectors.toList());
    }

    public static Map<String, List<Data>> readSheets(Sheets sheetsService, String spreadsheetId, String[] name) {
        return Arrays.stream(name)
                .collect(Collectors.toMap(name, n -> SheetReader.readSheet(sheetsService, spreadsheetId,(String) n)));
    }

    private static String[] listToArrayString(List<Object> list) {
        return list.stream()
                .map(o -> (String) o)
                .toArray(String[]::new);
    }
}
