package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ColumnServiceTest {

    private ColumnService service;


    private Column<?>[] generateColumnArray(int size) {
        String value = "value";
        String name = "name";
        return IntStream.range(0, 10).mapToObj(
                i -> new Column<>(value + (i + 1), name + (i + 1))
        ).toArray(Column<?>[]::new);
    }


    private List<Column<?>> getColumnsList(int size) {
        return Stream.of(generateColumnArray(size)).collect(Collectors.toList());
    }


    @BeforeEach
    private void initializaPrivateFields() {
        service = new ColumnService();
    }


    @Test
    void shouldAddColumn() {
        int expected = 1;
        int actual = service.addColumn(new Column<>("value", "name"), new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddColumnAddProperColumn() {
        Column<String> expected = new Column<>("value", "name");
        Column<?> actual = service.addColumn(expected, new ArrayList<>()).get(0);

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddColumnThrowExceptionWhenAttemptToAddNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addColumn(null, new ArrayList<>()));
    }


    @Test
    void shouldAddColumnThrowExceptionWhenAttemptToAddColumnToNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addColumn(new Column<>("value", "name"),null));
    }


    @Test
    void shouldAddColumns() {
        int expected = 10;
        int actual = service.addColumns(generateColumnArray(expected),
            new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddColumnsAddProperColumns() {
        Column<?>[] expected = generateColumnArray(10);
        Column<?>[] actual =  service.addColumns(expected,
            new ArrayList<>()).stream().toArray(Column<?>[]::new);

        assertArrayEquals(expected, actual);
    }


    @Test
    void shouldAddColumnsThrowExceptionWhenNullPassedInsteadOfColumnArray() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addColumns(null, new ArrayList<>()));
    }


    @Test
    void shouldAddColumnsThrowExceptionWhenAttemptToAddColumnsToNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.addColumns(generateColumnArray(2), null));
    }


    @Test
    void shouldAddColumnsThrowExceptionWhenAttemptToAddNull() {
        Column<?>[] columsToAdd = new Column<?>[] {
            new Column<>("value", "name"),
            null
        };

        assertThrows(IllegalArgumentException.class,
            () -> service.addColumns(columsToAdd, new ArrayList<>()));
    }


    @Test
    void shouldGetValueByNameReturnProperValueOnExistingColumnName() {
        String name = "name4";
        String expected = "value4";
        String actual = (String) service.getColumnByName(name, getColumnsList(10)).getValue();

        assertEquals(expected, actual);
    }


    @Test
    void shouldGetValueByNameThrowExceptionOnNotExistColumnName() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByName("notExist", getColumnsList(10)));
    }


    @Test
    void shouldGetValueByNameThrowExceptionWhenPassedColumnNameIsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByName(null, getColumnsList(10)));
    }


    @Test
    void shouldGetValueByNameThrowExceptionWhenPassedColumnsListIsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByName("nameToFind", null));
    }


    @Test
    void shouldGetValueByIndexReturnProperValueOnExistColumnIndex() {
        int indexToFind = 4;
        String expected = "value4";
        String actual = (String) service.getValueByIndex(indexToFind, getColumnsList(10));

        assertEquals(expected, actual);
    }


    @Test
    void shouldGetValueByIndexThrowsExceptionWhenIndexIsZero() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByIndex(0, getColumnsList(10)));
    }


    @Test
    void shouldGetValueByIndexThrowsExceptionWhenIndexIsBiggerThanActualColumnsNumber() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByIndex(11, getColumnsList(10)));
    }


    @Test
    void shouldGetValueByIndexThrowsExceptionWhenPassedColumnsListIsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> service.getValueByIndex(10, null));
    }



}