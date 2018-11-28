package com.codecool.SQLYourCSV.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AlertableTest {

    private ByteArrayOutputStream os;
    private PrintStream prevPs;
    private Alertable alertable;

    private final String CYAN = "\u001B[36m";
    private final String RED = "\u001B[31m";
    private final String DEFAULT_COLOR = "\u001B[0m";


    @BeforeEach
    void initPrivateFields() {
        prevPs = System.out;
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
    }


    @AfterEach
    void setPreviousPrintStream() {
        System.setOut(prevPs);
    }


    @Test
    void shouldMenuViewAlertMessage() {
        alertable = new MenuView();
        String messageToAlert = "Message to alert";
        alertMessage(messageToAlert, RED, DEFAULT_COLOR);
    }


    @Test
    void shouldDataViewAlertMessage() {
        alertable = new DataView();
        String messageToAlert = "Message to alert";
        alertMessage(messageToAlert, CYAN, DEFAULT_COLOR);
    }


    private void alertMessage(String msgToAlert, String alertColor, String defaultColor) {
        alertable.alert(msgToAlert);

        String expected = String.format("%s%s%s%n", alertColor, msgToAlert, defaultColor);
        String actual = os.toString();

        assertEquals(expected, actual);
    }
}