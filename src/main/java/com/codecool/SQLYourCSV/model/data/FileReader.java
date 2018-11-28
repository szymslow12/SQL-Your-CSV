package com.codecool.SQLYourCSV.model.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public static List<String[]> readFile(String file) throws IOException {
        if (file == null) throw new IllegalArgumentException("Expect String filename: got null");
        return Files.lines(getFilePath(file)).map(line -> line.split(",|:|;|\t")).collect(Collectors.toList());
    }


    private static Path getFilePath(String file) throws FileNotFoundException {
        URL url = FileReader.class.getClassLoader().getResource(file);
        if (url == null) throw new FileNotFoundException("File not found!");
        return Paths.get(url.getPath());
    }


    public static Map<String, List<String[]>> readFiles(String[] files){
        return Stream.of(files).collect(Collectors.toMap(file -> file, file -> {
            try {
                return readFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }));
    }
}
