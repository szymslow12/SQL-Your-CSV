package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.UserInputs;
import com.codecool.SQLYourCSV.view.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Controller
public class AppController {

    @Autowired
    private MenuView view;

    @Autowired
    private UserInputs inputs;

    @Autowired
    private QueryController queryController;


    public void setView(MenuView view) {
        this.view = view;
    }


    public void setUserInputs(UserInputs inputs) {
        this.inputs = inputs;
    }


    public void setQueryController(QueryController queryController) {
        this.queryController = queryController;
    }


    public void run() {
        boolean programIsRunning = true;

        while (programIsRunning) {

            view.showMenu(new String[]
                {
                    "Start query with chosen CSV file",
                    "Start query without chosen CSV file"
                }
            );

            switch (inputs.getNumber()) {
                case 1:
                    askForFileAndRun();
                    break;
                case 2:
                    queryController.run();
                    break;
                case 0:
                    programIsRunning = false;
                    break;
            }
        }
    }


    private void askForFileAndRun() {
        queryController.runWithFile(askForFile("Enter a full file name (file.csv):"));
    }


    private String askForFile(String message) {
        view.alert(message);
        String filename = inputs.getString();
        while (!filename.endsWith(".csv")) {
            view.alert(String.format("Bad filename format!%n%s", message));
            filename = inputs.getString();
        }
        return filename;
    }
}
