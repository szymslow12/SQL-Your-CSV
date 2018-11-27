package com.codecool.SQLYourCSV.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    private final String TAB = "\t";

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

        String expected = getExpectedMenu(labels);
        String actual = os.toString();

        assertEquals(expected, actual);
    }


    @Test
    void shouldShowMenuWithOneLabel() {
        String[] label = generateLables(1);
        view.showMenu(label);

        String expected = getExpectedMenu(label);
        String actual = os.toString();

        assertEquals(expected, actual);
    }


    @Test
    void shouldShowMenuWithThreeLabels() {
        String[] labels = generateLables(3);
        view.showMenu(labels);

        String expected = getExpectedMenu(labels);
        String actual = os.toString();

        assertEquals(expected, actual);
    }


    @Test
    void shouldShowMenuWithThousandLabels() {
        String[] labels = generateLables(1000);
        view.showMenu(labels);

        String expected = getExpectedMenu(labels);
        String actual = os.toString();

        assertEquals(expected, actual);
    }


    private String getExpectedMenu(String[] labels) {
        StringBuilder sB = new StringBuilder(TOP_MENU_LINE);
        //zrobic pierdolony clean tych linii
        IntStream.range(0, labels.length).forEach
            (i -> sB.append(String.format("%s%s%s%n", RED,
                    String.format("%s%s. %s", TAB, i + 1, labels[i]), DEFAULT_COLOR)));
        sB.append(EXIT_PROGRAM_LINE);
        sB.append(END_LINE);
        return sB.toString();
    }


    private String[] generateLables(int numberOfLables) {
        String[] lables = new String[numberOfLables];
        IntStream.range(0, numberOfLables).forEach(i -> lables[i] = String.format("Menu lables %s", i + 1));
        return lables;
    }
}