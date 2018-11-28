package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {


    @Test
    void shouldReadFileSeparatedByComma() {
        assertValuesAndValidate(getExpectedListResult(2), FileReader.readFile("test-file-comma.csv"));
    }


    @Test
    void shouldReadFileSeparatedByTabs() {
        assertValuesAndValidate(getExpectedListResult(2), FileReader.readFile("test-file-tabs.csv"));
    }


    private void assertValuesAndValidate(List<String[]> expected, List<String[]> actual) {
        IntStream.range(0, expected.size()).forEach(i -> assertArrayEquals(expected.get(i), actual.get(i)));
    }


    private List<String[]> getExpectedListResult(int numberOfRows) {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{"columnName1", "columnName2", "columnName3"});
        IntStream.range(0, numberOfRows).forEach(i -> result.add(new String[] {getRow(i), getRow(i), getRow(i)}));
        return result;
    }


    private String getRow(int rowNum) {
        //+ 1 becouse first row has number one not 0
        return String.format("row%s", rowNum + 1);
    }
}