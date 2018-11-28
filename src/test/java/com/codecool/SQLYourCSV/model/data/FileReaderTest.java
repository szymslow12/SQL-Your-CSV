package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {


    @Test
    void shouldReadFileSeparatedByComma() {
        List<String[]> expected = getExpectedListResult(2);
        List<String[]> actual = FileReader.readFile("test-file-comma.csv");
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