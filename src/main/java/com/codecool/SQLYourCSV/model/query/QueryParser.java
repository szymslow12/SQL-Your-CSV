package com.codecool.SQLYourCSV.model.query;

import com.codecool.SQLYourCSV.model.enumeration.Command;
import com.codecool.SQLYourCSV.model.enumeration.Rule;
import com.codecool.SQLYourCSV.model.enumeration.helpers.OperatorValues;
import com.codecool.SQLYourCSV.model.enumeration.helpers.Selector;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

        Stream.of(commands).forEach(command -> {
            List<String> queryPartsAsList = Arrays.asList(queryParts);
            if (command.getName().equalsIgnoreCase("SELECT")) {
                Object columns = IntStream.range(
                    findIndex(command, queryPartsAsList) + 1,
                    findIndex(Command.FROM, queryPartsAsList)
                ).mapToObj(queryPartsAsList::get).toArray(String[]::new);
                command.selector().setValue(columns);
            } else if (command.getName().equalsIgnoreCase("FROM")) {
                String tableName = queryPartsAsList.get(findIndex(command, queryPartsAsList) + 1);
                Object tableNameAsObject = checkAndRemoveSemicolon(tableName);
                command.selector().setValue(tableNameAsObject);
            } else if (command.getName().equalsIgnoreCase("FROM")) {
                OperatorValues operatorValues = new OperatorValues();

            }
        });
        query.setCommands(commands);
        return query;
    }


    private static boolean isCommand(String toCheck) {
        return Stream.of(Command.values()).anyMatch(command -> command.getName().equalsIgnoreCase(toCheck));
    }


    private static int findIndex(Command command, List<String> queryParts) {
        IntPredicate findIndexOfCommand = i -> command.getName().equalsIgnoreCase(queryParts.get(i));
        OptionalInt index = IntStream.range(0, queryParts.size()).filter(findIndexOfCommand).findFirst();
        if (index.isPresent()) {
            return index.getAsInt();
        }
        throw new IllegalArgumentException(String.format("Query is not valid missing %s", command.getName()));
    }


    private static String checkAndRemoveSemicolon(String toCheck) {
       return toCheck.contains(";") ? toCheck.substring(0, toCheck.indexOf(";")): toCheck;
    }
}
