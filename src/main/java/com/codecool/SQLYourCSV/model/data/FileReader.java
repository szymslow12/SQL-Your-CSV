package com.codecool.SQLYourCSV.model.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class FileReader {

    public static List<String[]> readFile(String file) {
        List<String[]> readFile = new ArrayList<>();
        try {
            URL url = FileReader.class.getClassLoader().getResource(file);
            if (url == null) throw new FileNotFoundException("File not found!");
            Scanner scanner = new Scanner(new File(url.getFile()));
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
