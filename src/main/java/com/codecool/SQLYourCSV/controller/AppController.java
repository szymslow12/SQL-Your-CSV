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
        view.showMenu(new String[]
            {
                "Start query with chosen CSV file",
                "Start query without chosen CSV file"
            }
        );

        inputs.getString();
    }
}
