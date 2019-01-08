package com.codecool.SQLYourCSV.model.query;

import com.codecool.SQLYourCSV.model.enumeration.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryParser {

    public static Query parse(String toParse) {
        if (toParse == null) {
            throw new IllegalArgumentException("Query is null!");
        }
        if (toParse.charAt(toParse.length() - 1) != ";".charAt(0)) {
            throw new IllegalArgumentException("Missing semicolon!");
        }
        Query query = new Query();
        String[] queryParts = toParse.split(" |, |,");

        System.out.println(Arrays.toString(queryParts));
        Stream.of(queryParts).forEach(p -> System.out.println(p));
        commandList.forEach(e -> System.out.println(e));
        return null;
    }
}
