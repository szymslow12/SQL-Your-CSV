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
    private final String TOP_MENU_LINE = String.format("%s%s%s%n", RED, "WELCOME IN MAIN MENU\n", DEFAULT_COLOR);
    private final String EXIT_PROGRAM_LINE = String.format("%s%s%s%n", RED, "\t0. Exit program", DEFAULT_COLOR);
    private final String END_LINE = String.format("%n%s%s%s", RED, "Enter a number: ", DEFAULT_COLOR);

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


    @Test
    void shouldShowMenuWithoutGivenLabels() {
        String[] labels = new String[0];
        view.showMenu(labels);

        String expected = String.format("%s%s%s",
                TOP_MENU_LINE,
                EXIT_PROGRAM_LINE,
                END_LINE);

        String actual = os.toString();

        assertEquals(expected, actual);
    }


}