package com.codecool.SQLYourCSV.model.query;

import com.codecool.SQLYourCSV.model.enumeration.Command;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;
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
        Predicate<String> findCommand = queryPart -> Stream.of(Command.values()).anyMatch(
                command -> queryPart.equalsIgnoreCase(command.getName())
        );
        Function<String, Command> mapToCommand = queryPart -> Command.valueOf(queryPart.toUpperCase());

        Command[] commands = Stream.of(queryParts).filter(findCommand).
            map(mapToCommand).toArray(Command[]::new);

        System.out.println(Arrays.toString(queryParts));
        Stream.of(queryParts).forEach(p -> System.out.println(p));
        Stream.of(commands).forEach(e -> System.out.println(e.selector().getValue()));
        Stream.of(commands).forEach(command -> {
            List<String> queryPartsAsList = Arrays.asList(queryParts);
            IntStream.range(findIndex(command, queryPartsAsList), queryPartsAsList.size()).map(i -> );
        });
        query.setCommands(commands);
        return null;
    }


    private static boolean isCommand(String toCheck) {
        return Stream.of(Command.values()).anyMatch(command -> command.getName().equalsIgnoreCase(toCheck));
    }


    private static int findIndex(Command command, List<String> queryParts) {
        IntPredicate findIndex = i -> command.getName().equalsIgnoreCase(queryParts.get(i));
        OptionalInt index = IntStream.range(0, queryParts.size()).filter(findIndex).findFirst();
        if (index.isPresent()) {
            return index.getAsInt();
        }
        throw new IllegalArgumentException(String.format("Query is not valid missing %s", command.getName()));
    }
}
