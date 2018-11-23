package com.codecool.SQLYourCSV.model;

import java.util.Scanner;
public class UserInputs {

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
