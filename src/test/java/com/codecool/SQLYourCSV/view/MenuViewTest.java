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
        String expected = String.format("\u001B[31m%s\u001B[0m%n", messageToAlert);
        String actual = os.toString();
        assertEquals(expected, actual);
    }
}