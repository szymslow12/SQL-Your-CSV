package com.codecool.SQLYourCSV.model;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class UserInputs {

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public int getNumber() {
        Scanner scanner = new Scanner(System.in);
        boolean isCorrectInput = false;
        int toReturn = 0;
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
