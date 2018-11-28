package com.codecool.SQLYourCSV.model.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {

    public static List<String[]> readFile(String file) {
        List<String[]> readFile = new ArrayList<>();
        try {

            Files.lines(getFilePath(file)).forEach(line -> readFile.add(line.split(",|:|;|\t")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return readFile;
    }


    private static Path getFilePath(String file) throws FileNotFoundException {
        URL url = FileReader.class.getClassLoader().getResource(file);
        if (url == null) throw new FileNotFoundException("File not found!");
        return Paths.get(url.getPath());
    }


    public static Map<String, List<String[]>> readFiles(String[] files) {
        return null;
    }
}
