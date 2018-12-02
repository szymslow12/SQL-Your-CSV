package com.codecool.SQLYourCSV.model.datapresentation;

public class Column<T> {

    private T value;
    private String name;

    public Column(T value, String name) {
        this.value = value;
        this.name = name;
    }


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


    @Override
    public String toString() {
        return value.toString();
    }


    @Override
    public boolean equals(Object toComapre) {
        Column<?> columnToCompare;
        if (toComapre instanceof Column) {
            columnToCompare = (Column<?>) toComapre;
            System.out.println(this.hashCode() + " " + columnToCompare.hashCode());
            return this.hashCode() == columnToCompare.hashCode();
        }
        return false;
    }


    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.value.hashCode();
        hashCode = hashCode * 37 + this.name.hashCode();

        return hashCode;
    }
}
