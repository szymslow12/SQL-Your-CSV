package com.codecool.SQLYourCSV.model;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class UserInputs {

    public String getString() {
        Scanner scanner = new Scanner(System.in);
        String toReturn = scanner.nextLine();
        scanner.close();
        return toReturn;
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
        scanner.close();
        return toReturn;
    }
}
