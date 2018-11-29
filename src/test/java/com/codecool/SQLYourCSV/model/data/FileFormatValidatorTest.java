package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileFormatValidatorTest {

    @Test
    void shouldValidateReturnTrueOnCorrectCSV_CommaCase() {
        assertTrue(FileFormatValidator.validate("text,separated,by,comma"));
    }

}