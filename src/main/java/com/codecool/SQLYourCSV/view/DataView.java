package com.codecool.SQLYourCSV.view;

import com.codecool.SQLYourCSV.model.datastructure.Table;
import org.springframework.stereotype.Component;

@Component
public class DataView implements Alertable {

    public void alert(String msg) {
        System.out.println(String.format("\u001B[36m%s\u001B[0m", msg));
    }

    public void showTable(Table table) {
        System.out.println(table.toString());
    }
}
