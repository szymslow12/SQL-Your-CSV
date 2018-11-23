package com.codecool.SQLYourCSV.controller;

import org.springframework.beans.factory.annotation.Autowired;

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

    }
}
