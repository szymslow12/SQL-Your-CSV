package com.codecool.SQLYourCSV.model.query2;

import com.codecool.SQLYourCSV.model.enumeration.Statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {

    private List<String> splitedQuery = new ArrayList<>();
    private Query queryObject = new Query();
    private String query;

    public QueryParser(String query) {
        createQueryList(query);
        this.query = query;
    }

    public void queryParser() {

        if (!checkSelectQueryIsProper()) throw new IllegalArgumentException("Query is no proper!");

        String statement = getStatement().toUpperCase();
        queryObject.setStatement(statement);

        String[] columnsName = getColumnsName();
        queryObject.setColumns(columnsName);

        String tableName = getTableName();
        queryObject.setTableName(tableName);

        System.out.println(queryObject.toString());

    }

    private void createQueryList(String query) {
        String[] split = query.toUpperCase().split(" +|, +|,");
        Collections.addAll(splitedQuery, split);
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
        throw new IllegalArgumentException("UNEXPECTED ERROR 505 !!");
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

    private boolean checkSelectQueryIsProper() {
        Pattern selectRegex = Pattern.compile("SELECT (.*) FROM (\\w*)( WHERE .+?=?'.+')?;", Pattern.CASE_INSENSITIVE);
        return selectRegex.matcher(query).find();
    }

    private String[] getColumnsName() {
        String group = "";
        String statement = queryObject.getStatement();
        Pattern columnsName = Pattern.compile(statement + " (.*) FROM .*" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(query);
        if(matcher.matches()) {
            group = matcher.group(1);
        }
        return group.trim().split( "\\s+,\\s+");
    }

    private String getTableName() {
        String group = "";
        Pattern columnsName = Pattern.compile(".* FROM (.*) (?:WHERE .*=?'.*');" , Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(query);
        if(matcher.matches()) {
            group = matcher.group(1);
        }
        return group;
    }

}
