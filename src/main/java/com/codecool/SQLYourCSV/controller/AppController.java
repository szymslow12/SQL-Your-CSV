package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.UserInputs;
import com.codecool.SQLYourCSV.view.MenuView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {

    @Autowired
    private MenuView view;

    @Autowired
    private UserInputs inputs;


    public void setView(MenuView view) {
        this.view = view;
    }


    public void setUserInputs(UserInputs inputs) {
        this.inputs = inputs;
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
                    QueryController.run();
                    break;
                case 0:
                    programIsRunning = false;
                    break;
            }
        }
    }


    private void askForFileAndRun() {
        view.alert("Enter a full file name (file.csv):");
        QueryController.runWithFile(inputs.getString());
    }
}
