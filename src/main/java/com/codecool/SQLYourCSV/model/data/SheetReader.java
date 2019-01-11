package com.codecool.SQLYourCSV.model.data;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SheetReader {
    public static List<String[]> readSheet(Sheets sheetsService, String spreadsheetId, String name) throws IOException {
            ValueRange vr = sheetsService.spreadsheets().values().get(spreadsheetId, name).execute();
            return vr.getValues().stream()
                    .map(SheetReader::listToArrayString)
                    .collect(Collectors.toList());
    }

    public static Map<String, List<String[]>> readSheets(Sheets sheetsService, String spreadsheetId, String[] names) {
        return Stream.of(names)
                .collect(Collectors.toMap(name -> name, name -> {
                    try {
                        return readSheet(sheetsService, spreadsheetId,name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return new ArrayList<>();
                }));
    }

    private static String[] listToArrayString(List<Object> list) {
        return list.stream()
                .map(o -> (String) o)
                .toArray(String[]::new);
    }
}
