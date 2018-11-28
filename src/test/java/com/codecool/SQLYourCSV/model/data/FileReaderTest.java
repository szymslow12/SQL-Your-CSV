package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {


    private final int ROWS_IN_TEST_FILE = 2;

    @Test
    void shouldReadFileSeparatedByComma() {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE),
            FileReader.readFile("test-file-comma.csv"));
    }


    @Test
    void shouldReadFileSeparatedByTabs() {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE),
            FileReader.readFile("test-file-tabs.csv"));
    }


    @Test
    void shouldReadFileSeparatedByColons() {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE),
            FileReader.readFile("test-file-colons.csv"));
    }


    @Test
    void shouldReadFileSeparatedBySemicolons() {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE),
            FileReader.readFile("test-file-semicolons.csv"));
    }


    @Test
    void shouldReadFileReturnProperSplittedLinesByComma() {
        assertValuesAndValidateArraysElements(getExpectedListResult(ROWS_IN_TEST_FILE),
            FileReader.readFile("test-file-comma.csv"));
    }


    private void assertValuesAndValidateArraysLength(List<String[]> expected, List<String[]> actual) {
        IntStream.range(0, expected.size()).forEach(i -> assertArrayEquals(expected.get(i), actual.get(i)));
    }


    private void assertValuesAndValidateArraysElements(List<String[]> expected, List<String[]> actual) {
        IntStream.range(0, expected.size()).forEach(i -> IntStream.range(0, expected.get(i).length).forEach(j -> assertEquals(expected.get(i)[j], actual.get(i)[j])));
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