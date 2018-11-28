package com.codecool.SQLYourCSV.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DataViewTest {

    private ByteArrayOutputStream os;
    private PrintStream prevPs;

    private DataView view;
    private final String CYAN = "\u001B[36m";
    private final String DEFAULT_COLOR = "\u001B[0m";

    @BeforeEach
    void initPrivateFields() {
        prevPs = System.out;
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        view = new DataView();
    }


    @AfterEach
    void setPreviousPrintStream() {
        System.setOut(prevPs);
    }
}