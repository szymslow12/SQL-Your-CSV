package com.codecool.SQLYourCSV.model.query;

import com.codecool.SQLYourCSV.model.enumeration.Statements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QueryParser {

    private List<String> splitedQuery = new ArrayList<>();
    private Query queryObject = new Query();

    public QueryParser(String query) {
        createQueryList(query);
    }

    public static Query parse(String query) {
        return new Query();
    }

    public void queryParser() {
        checkQueryIsProper();

        String statement = getStatement().toUpperCase();

        queryObject.setStatement(statement);

        deleteStatementFromQuery(statement);

        String[] columnsName = getColumnsName();

        queryObject.setColumns(columnsName);

        System.out.println(queryObject.toString());

    }

    private void createQueryList(String query) {
        String[] split = query.toUpperCase().split(" +|, +|,");
        Collections.addAll(splitedQuery, split);
    }

    private void checkQueryIsProper() {
        if (isEmpty()) throw new IllegalArgumentException("Query is not complete!");
        if (!isSemicolonAtEnd()) throw new IllegalArgumentException("Missing semicolon!");
        if (!isStatementInQuery()) throw new IllegalArgumentException("Any statement in query or in wrong position");
        if (!isFromKeywordInQuery()) throw new IllegalArgumentException("Missing from keyword!");
        if(missingColumnNames()) throw new IllegalArgumentException("Columns names in wrong position or any columns name are entered!");
    }

    private boolean isEmpty() {
        return splitedQuery.size() == 1;
    }

    private boolean isSemicolonAtEnd() {
        int lastElementIndex = splitedQuery.size() - 1;
        return splitedQuery.get(lastElementIndex).contains(";");
    }

    private boolean isStatementInQuery() {
        int stmtIndexPos1 = 0;
        int stmtIndexPos2 = 1;
        Statements[] statements = Statements.values();
        for (Statements statement : statements) {
            if (statement.getStatement().equalsIgnoreCase(splitedQuery.get(stmtIndexPos1))) {
                return true;
            }
            String twoWordStatement = getTwoWordStatement(stmtIndexPos1, stmtIndexPos2);
            if (statement.getStatement().equalsIgnoreCase(twoWordStatement)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFromKeywordInQuery() {
        for (String s : splitedQuery) {
            if (s.equalsIgnoreCase("from")) return true;
        }
        return false;
    }

    private boolean missingColumnNames() {
        return splitedQuery.get(0).equalsIgnoreCase("from");
    }

    private String getStatement() {
        int stmtIndexPos1 = 0;
        int stmtIndexPos2 = 1;
        Statements[] statements = Statements.values();
        for (Statements statement : statements) {
            String twoWordStatement = getTwoWordStatement(stmtIndexPos1, stmtIndexPos2);
            if (statement.getStatement().equalsIgnoreCase(twoWordStatement)) {
                return twoWordStatement;
            }
            if (statement.getStatement().equalsIgnoreCase(splitedQuery.get(stmtIndexPos1))) {
                return (String.valueOf(statement));
            }
        }
        return null;
    }

    private String getTwoWordStatement(int stmtIndexPos1, int stmtIndexPos2) {
        return splitedQuery.get(stmtIndexPos1) + " " + splitedQuery.get(stmtIndexPos2);

    }

    private void deleteStatementFromQuery(String statement) {
        String[] s = statement.split(" ");
        for (String word : s) {
            splitedQuery.remove(word);
        }
    }

    private String[] getColumnsName() {
        int fromKeyWordIndex = getFromKeywordIndex();
        String[] strings = splitedQuery.stream().toArray(String[]::new);
        return Arrays.copyOf(strings, fromKeyWordIndex);

    }

    private int getFromKeywordIndex() {
        for(int i = 0; i < splitedQuery.size(); i++){
            if(splitedQuery.get(i).equalsIgnoreCase("FROM")) return i;
        }
        return 0;
    }

}
