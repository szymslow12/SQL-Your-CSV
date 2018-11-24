package com.codecool.SQLYourCSV.view;

public class DataView implements Alertable {

    public void alert(String msg) {
        System.out.println(String.format("\u001B[36m%s\u001B[0m", msg));
    }
}
