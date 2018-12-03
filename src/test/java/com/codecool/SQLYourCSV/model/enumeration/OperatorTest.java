package com.codecool.SQLYourCSV.model.enumeration;

import com.codecool.SQLYourCSV.model.datastructure.Column;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    @Test
    void shouldEQUALS_CompareProperlySameColumn_OneReference() {
        Column<String> toCompare = new Column<>("value", "name");

        assertTrue(Operator.EQUALS.compare(toCompare, toCompare));
    }

}