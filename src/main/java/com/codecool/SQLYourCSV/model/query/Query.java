package com.codecool.SQLYourCSV.model.query;

public class Query {

    private Command[] commands;

    public void setCommands(Command[] commands) {
        this.commands = commands;
    }


    public Command[] getCommands() {
        return commands;
    }


    public boolean contains(Command toFind) {
        return false;
    }
}
