package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileFormatValidatorTest {

    @Test
    void shouldValidateReturnTrueOnCorrectCSV_CommaCase() {
        assertTrue(FileFormatValidator.validate("text,separated,by,comma"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_SemicolonCase() {
        assertTrue(FileFormatValidator.validate("text;separated;by;comma"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_ColonCase() {
        assertTrue(FileFormatValidator.validate("text:separated:by:comma"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_TabCase() {
        assertTrue(FileFormatValidator.validate("text   separated   by  comma"));
    }


    @Test
    void shouldValidateReturnFalseOnInCorrectCSV() {
        assertFalse(FileFormatValidator.validate("this should not pass"));
    }


    @Test
    void shouldValidateThrowExceptionWhenNullPassed() {
        assertThrows(IllegalArgumentException.class, () -> FileFormatValidator.validate(null));
    }

}