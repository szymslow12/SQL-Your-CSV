package com.codecool.SQLYourCSV.model;

import java.util.InputMismatchException;
import java.util.Scanner;
public class UserInputs {

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public int getNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean isCorrectInput = false;
        Integer toReturn = 0;
        while (!isCorrectInput) {
            try {
                toReturn = scanner.nextInt();
                isCorrectInput = true;
            } catch (InputMismatchException err) {
                System.out.println("Wrong input, number needed");
            }
        }
        return toReturn;
    }
}
