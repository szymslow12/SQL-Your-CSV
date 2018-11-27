package com.codecool.SQLYourCSV.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputsTest {

    private InputStream prevIS;
    private UserInputs userInputs;

    @BeforeEach
    void initPrivateFields() {
        prevIS = System.in;
        userInputs = new UserInputs();
    }


    @AfterEach
    void setPrevIS() {
        System.setIn(prevIS);
    }

    @Test
    void shouldGetStringReturnString() {
        String expected = "expected result";
        System.setIn(new ByteArrayInputStream(expected.getBytes()));
        String actual = userInputs.getString();

        assertEquals(expected, actual);
    }


    @Test
    void shouldGetNumberReturnNumber() {
        int expected = 1;
        System.setIn(new ByteArrayInputStream(Integer.toString(expected).getBytes()));
        int actual = userInputs.getNumber();

        assertEquals(expected, actual);
    }
}