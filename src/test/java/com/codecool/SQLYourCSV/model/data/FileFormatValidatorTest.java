package com.codecool.SQLYourCSV.model.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileFormatValidatorTest {

    @Test
    void shouldValidateReturnTrueOnCorrectCSV_CommaCase() {
        assertTrue(FileFormatValidator.validate("text,separated,by,commas"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_SemicolonCase() {
        assertTrue(FileFormatValidator.validate("text;separated;by;semicolons"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_ColonCase() {
        assertTrue(FileFormatValidator.validate("text:separated:by:colons"));
    }


    @Test
    void shouldValidateReturnTrueOnCorrectCSV_TabCase() {
        assertTrue(FileFormatValidator.validate("text\tseparated\tby\ttabs"));
    }


    @Test
    void shouldValidateReturnFalseOnIncorrectCSV_NoDelimiterCase() {
        assertFalse(FileFormatValidator.validate("this should not pass"));
    }


    @Test
    void shouldValidateReturnFalseOnIncorrectCSV_MultipleDelimiterTypesCase() {
        assertFalse(FileFormatValidator.validate("this,should;not:pass"));
    }

    @Test
    void shouldValidateThrowExceptionWhenNullPassed() {
        assertThrows(IllegalArgumentException.class, () -> FileFormatValidator.validate(null));
    }

}