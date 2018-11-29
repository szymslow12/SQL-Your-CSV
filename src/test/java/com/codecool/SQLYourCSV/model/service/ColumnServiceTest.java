package com.codecool.SQLYourCSV.model.service;

import com.codecool.SQLYourCSV.model.datapresentation.Column;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ColumnServiceTest {

    private ColumnService service;

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
        int actual = service.addColumns(generateColumnArray(expected), new ArrayList<>()).size();

        assertEquals(expected, actual);
    }


    @Test
    void shouldAddColumnsAddProperColumns() {
        Column<?>[] expected = generateColumnArray(10);
        Column<?>[] actual =  service.addColumns(expected, new ArrayList<>()).stream().toArray(Column<?>[]::new);

        assertArrayEquals(expected, actual);
    }

    private Column<?>[] generateColumnArray(int size) {
        String value = "value";
        String name = "name";
        return IntStream.range(0, 10).mapToObj(
            i -> new Column<>(value + (i + 1), name + (i + 1))
        ).toArray(Column<?>[]::new);
    }
}