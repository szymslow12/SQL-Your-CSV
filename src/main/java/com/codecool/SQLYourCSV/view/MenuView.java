package com.codecool.SQLYourCSV.view;

import java.util.stream.IntStream;

public class MenuView implements Alertable {

    public void alert(String msg) {
        System.out.println(String.format("\u001B[31m%s\u001B[0m", msg));
    }


    public void showMenu(String[] labels) {
        alert("MAIN MENU\n");
        IntStream.range(0, labels.length).forEach(i -> alert(String.format("\t%s. %s", i, labels[i])));
        System.out.print("\nEnter a number: ");
    }
}
