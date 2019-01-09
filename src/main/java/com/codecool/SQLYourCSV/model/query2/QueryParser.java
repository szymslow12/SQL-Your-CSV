package com.codecool.SQLYourCSV.model.query2;

import com.codecool.SQLYourCSV.model.enumeration.Statements;

import java.util.Arrays;
import java.util.List;

public class QueryParser {

    private List<String> splitedQuery;
    private Query queryObject = new Query();

    public QueryParser(String query) {
        this.splitedQuery = getSplitedQuery(query);
    }

    public void queryParser() {
        checkQueryIsProper();

        String statement = getStatement().toUpperCase();

        queryObject.setStatement(statement);

        deleteStatementFromQuery(statement);

        getColumnsName();

        System.out.println(queryObject.toString());

    }

    private List<String> getSplitedQuery(String query) {
        return Arrays.asList(query.split(" +|, +|,"));
    }

    private void checkQueryIsProper() {
        if (isEmpty()) throw new IllegalArgumentException("Query is not complete!");
        if (!isSemicolonAtEnd()) throw new IllegalArgumentException("Missing semicolon!");
        if (!isStatementInQuery()) throw new IllegalArgumentException("Any statement in query or in wrong position");
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
        for (String string : s) {
            splitedQuery.remove(string);
        }
    }

    private void getColumnsName() {

    }

}
