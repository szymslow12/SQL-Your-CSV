package com.codecool.SQLYourCSV.model.data;

import java.util.stream.Stream;

//Interface Should Be Fine for this -> interface FileFormatValidator -> CSVFormatValidator :D
public class FileFormatValidator {
    //ADD THIS FIELD TO UML!
    private static String[] possibleFormats = {
        "^([\\w\\d-_@! ]+,{1})+([\\w\\d-_@!]+)$",
        "^([\\w\\d-_@! ]+;{1})+([\\w\\d-_@!]+)$",
        "^([\\w\\d-_@! ]+\t{1})+([\\w\\d-_@!]+)$"
    };

    public static boolean validate(String toValid) {
        if (toValid == null) throw new IllegalArgumentException("Expect String toValid: got null");
        return Stream.of(possibleFormats).anyMatch(pattern -> toValid.matches(pattern));
    }
}
