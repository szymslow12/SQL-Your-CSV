package com.codecool.SQLYourCSV.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DataViewTest {

    private ByteArrayOutputStream os;
    private PrintStream prevPs;

    private DataView view;

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