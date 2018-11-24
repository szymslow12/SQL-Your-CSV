package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.UserInputs;
import com.codecool.SQLYourCSV.model.service.TableService;
import com.codecool.SQLYourCSV.view.DataView;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryController {

    @Autowired
    private DataView view;

    @Autowired
    private UserInputs inputs;

    @Autowired
    private TableService service;


    public void setView(DataView view) {
        this.view = view;
    }


    public void setUserInputs(UserInputs inputs) {
        this.inputs = inputs;
    }


    public void setService(TableService service) {
        this.service = service;
    }


    public void run() {

    }


    public void runWithFile(String file) {

    }
}
