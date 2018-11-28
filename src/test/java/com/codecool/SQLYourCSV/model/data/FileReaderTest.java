package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {


    private final int ROWS_IN_TEST_FILE = 2;
    private final int COLUMNS_IN_TEST_FILE = 3;

    @Test
    void shouldReadFileSeparatedByComma() throws IOException {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-comma.csv"));
    }


    @Test
    void shouldReadFileSeparatedByTabs() throws IOException {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-tabs.csv"));
    }


    @Test
    void shouldReadFileSeparatedByColons() throws IOException {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-colons.csv"));
    }


    @Test
    void shouldReadFileSeparatedBySemicolons() throws IOException {
        assertValuesAndValidateArraysLength(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-semicolons.csv"));
    }


    @Test
    void shouldReadFileReturnProperSplittedLinesByComma() throws IOException {
        assertValuesAndValidateArraysElements(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-comma.csv"));
    }


    @Test
    void shouldReadFileReturnProperSplittedLinesByTabs() throws IOException {
        assertValuesAndValidateArraysElements(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-tabs.csv"));
    }


    @Test
    void shouldReadFileReturnProperSplittedLinesByColons() throws IOException {
        assertValuesAndValidateArraysElements(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-colons.csv"));
    }


    @Test
    void shouldReadFileReturnProperSplittedLinesBySemicolons() throws IOException {
        assertValuesAndValidateArraysElements(getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE),
            FileReader.readFile("test-file-semicolons.csv"));
    }


    @Test
    void shouldReadFileThrowExceptionWhenNullPassed() {
        assertThrows(IllegalArgumentException.class, () -> FileReader.readFile(null));
    }


    @Test
    void shouldReadFilesReturnProperFilesWithSameFormat_TwoFilesCase() {
        Map<String, List<String[]>> actual = FileReader.readFiles(new String[]{"test-file-comma.csv", "test-file-tabs.csv"});
        List<String[]> expected = getExpectedListResult(ROWS_IN_TEST_FILE, COLUMNS_IN_TEST_FILE);
        actual.values().forEach(value -> assertValuesAndValidateArraysElements(expected, value));
    }


    @Test
    void shouldReadFileReadTwoFilesWithDifferentFormat() {

    }


    private void assertValuesAndValidateArraysLength(List<String[]> expected, List<String[]> actual) {
        IntStream.range(0, expected.size()).forEach(i -> assertArrayEquals(expected.get(i), actual.get(i)));
    }


    private void assertValuesAndValidateArraysElements(List<String[]> expected, List<String[]> actual) {
        IntStream.range(0, expected.size()).forEach(i -> IntStream.range(0, expected.get(i).length).forEach(j -> assertEquals(expected.get(i)[j], actual.get(i)[j])));
    }


    private List<String[]> getExpectedListResult(int numberOfRows, int numberOfColumns) {
        List<String[]> result = new ArrayList<>();
        result.add(IntStream.range(0, numberOfColumns).mapToObj(i -> String.format("columnName%s", i+1)).toArray(String[]::new));
        IntStream.range(0, numberOfRows).forEach(i -> result.add(IntStream.range(0, numberOfColumns).mapToObj(j -> getRow(i)).toArray(String[]::new)));
        return result;
    }


    private String getRow(int rowNum) {
        //+ 1 becouse first row numbers starts from 1 not 0
        return String.format("row%s", rowNum + 1);
    }
}