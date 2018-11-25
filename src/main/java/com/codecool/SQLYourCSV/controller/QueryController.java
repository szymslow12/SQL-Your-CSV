package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.UserInputs;
import com.codecool.SQLYourCSV.model.service.TableService;
import com.codecool.SQLYourCSV.view.DataView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
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
        view.alert("Type \"\\\"q to exit.\nEnter your query: ");
        // Another Class WIth STATIC METHOD VALIDATE(String) INPUT -> can use in UserInputs
        view.showTable(service.createTableFromQuery(QueryParser.parser(inputs.getString())));
    }


    public void runWithFile(String file) {
        // HERE TO CAN BE USED THAH CLASS TO VALIDATAE INPUT
        service.getData().loadFromFile(inputs.getString());
        run();
    }
}
