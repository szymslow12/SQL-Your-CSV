package com.codecool.SQLYourCSV.model.datapresentation;

public class Column<T> {

   private T value;
   private String name;

    public T getValue() {
        return value;
    }


    public void setValue(T value) {
        this.value = value;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name= name;
    }
}