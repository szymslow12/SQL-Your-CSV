package com.codecool.SQLYourCSV.model.query2;

import com.codecool.SQLYourCSV.model.enumeration.Statements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {

    private String query;
    Query queryObject = new Query();

    public QueryParser(String query) {
        this.query = query.toUpperCase();
    }

    public void queryParser() {

        if (isEmpty()) throw new IllegalArgumentException("Query is null!");
        if (lackSemicolonAtEnd()) throw new IllegalArgumentException("Missing semicolon!");

        String statement = getStatement();
        if (statement == null) {
            throw new IllegalArgumentException("Wrong or missing statement");
        } else queryObject.setStatement(getStatement());



        System.out.println(queryObject.toString());

    }

    private boolean isEmpty() {
        return query == null;
    }

    private boolean lackSemicolonAtEnd() {
        return query.charAt(query.length() - 1) != ";".charAt(0);
    }

    private String getStatement() {
        int maxStatementSize = 16;
        String possibleStatement = query.substring(0, maxStatementSize);
        Statements[] values = Statements.values();
        for (Statements value : values) {
            if (possibleStatement.contains(value.getStatement())) {
                return value.getStatement();
            }
        }
        return null;

    }

    private String[] getColumns() {
        String statement = queryObject.getStatement();
        Pattern p = Pattern.compile("\\(.*?\\)");
        Matcher m = p.matcher(statement);
    }
}
