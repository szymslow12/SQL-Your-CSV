package com.codecool.SQLYourCSV.view;

public class MenuView implements Alertable {

    public void alert(String msg) {
        System.out.println(String.format("\u001B[31m%s\u001B[0m", msg));
    }
}
