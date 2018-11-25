package com.codecool.SQLYourCSV.view;

import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class MenuView implements Alertable {

    public void alert(String msg) {
        System.out.println(String.format("\u001B[31m%s\u001B[0m", msg));
    }


    public void showMenu(String[] labels) {
        alert("WELCOME IN MAIN MENU\n");
        IntStream.range(0, labels.length).forEach(i -> alert(String.format("\t%s. %s", i + 1, labels[i])));
        alert("\t0. Exit program");
        System.out.print("\n\u001B[31mEnter a number: \u001B[0m");
    }
}
