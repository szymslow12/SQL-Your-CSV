package com.codecool.SQLYourCSV.model.data;

//Interface Should Be Fine for this -> interface FileFormatValidator -> CSVFormatValidator :D
public class FileFormatValidator {

    public static boolean validate(String toValid) {
        return toValid.matches("^[^\\s,][^,]*[^\\s,](,[^\\s,][^,]*[^\\s,])*$");
    }
}
