package com.codecool.SQLYourCSV.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuViewTest {

    private ByteArrayOutputStream os;
    private PrintStream prevPs;

    private MenuView view;
    private final String RED = "\u001B[31m";
    private final String DEFAULT_COLOR = "\u001B[0m";

    @BeforeEach
    void initPrivateFields() {
        prevPs = System.out;
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        view = new MenuView();
    }

    @AfterEach
    void setPreviousPrintStream() {
        System.setOut(prevPs);
    }


    @Test
    void shouldAlertMessage() {
        String messageToAlert = "Message to alert";
        view.alert(messageToAlert);

        String expected = String.format("%s%s%s%n", RED, messageToAlert, DEFAULT_COLOR);
        String actual = os.toString();

        assertEquals(expected, actual);
    }
}