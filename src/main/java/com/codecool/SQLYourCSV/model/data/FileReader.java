package com.codecool.SQLYourCSV.model.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    public static List<String[]> readFile(String file) {
        List<String[]> readFile = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FileReader.class.getClassLoader().getResource(file).getFile()));
            while (scanner.hasNext()) {
                readFile.add(scanner.nextLine().split(",|:|;|\t"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return readFile;
    }


    public static Map<String, List<String[]>> readFiles(String[] files) {
        return null;
    }
}
