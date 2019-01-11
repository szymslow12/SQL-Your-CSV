package com.codecool.SQLYourCSV.model.query;

import com.codecool.SQLYourCSV.model.enumeration.Statements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {

    private List<String> splitedQuery = new ArrayList<>();
    private Query queryObject = new Query();
    private String queryString;

    public static Query parse(String query) {
        return new QueryParser().queryParser(query); }

    public Query queryParser(String query) {
        this.queryString = query;
        createQueryList();
        deleteTooMuchSpaces();
        if (!checkSelectQueryIsProper()) throw new IllegalArgumentException("Query is no proper!");

        String statement = getStatement().toUpperCase();
        queryObject.setStatement(statement);

        String[] columnsName = getColumnsName();
        queryObject.setColumns(columnsName);

        String tableName = getTableName();
        queryObject.setTableName(tableName);

        if (checkClauseExist()) {
            String clauseName = getClauseName();
            queryObject.setClauseName(clauseName);
            String clauseCondition = getClauseCondition();
            queryObject.setClauseCondition(clauseCondition);
            String clauseValue = getClauseValue();
            queryObject.setClauseValue(clauseValue);
        }

        System.out.println(queryObject.toString());

        return queryObject;
    }

    private void createQueryList() {
        String[] split = queryString.toUpperCase().split(" +|, +|,");
        Collections.addAll(splitedQuery, split);
    }

    private void deleteTooMuchSpaces() {
        this.queryString = queryString.replaceAll("\\s+", " ");
        this.queryString = queryString.replaceAll(" ,", ",");
        this.queryString = queryString.replaceAll(", ", ",");
        this.queryString = queryString.replaceAll(" =", "=");
        this.queryString = queryString.replaceAll("= ", "=");
        this.queryString = queryString.replaceAll(" ;", ";");
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

    private boolean checkClauseExist() {
        String tableName = queryObject.getTableName();
        Pattern selectRegex = Pattern.compile("FROM " + tableName + ".*", Pattern.CASE_INSENSITIVE);
        return selectRegex.matcher(queryString).find();

    }

    private boolean checkSelectQueryIsProper() {
        Pattern selectRegex = Pattern.compile("SELECT (?!select)(([a-zA-Z*][a-zA-Z0-9_.*-]*)(?:,[a-zA-Z][a-zA-Z0-9_.-]*)?)* " +
                "FROM ([a-zA-Z][a-zA-Z0-9_.])+" +
                "(?: WHERE [a-zA-Z][a-zA-Z0-9_.-]+='[a-zA-Z][a-zA-Z0-9_.]*')?;", Pattern.CASE_INSENSITIVE);

        return selectRegex.matcher(queryString).find();
    }

    private String[] getColumnsName() {
        String group = "";
        String statement = queryObject.getStatement();
        Pattern columnsName = Pattern.compile(statement + " (.*) FROM.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(queryString);
        if (matcher.matches()) {
            group = matcher.group(1);
        }
        return group.split(",");
    }

    private String getTableName() {
        String tableName = "";
        Pattern columnsName = Pattern.compile(".*FROM (\\w+)(?: WHERE.*)?;", Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(queryString);
        if (matcher.matches()) {
            tableName = matcher.group(1);
        }
        return tableName;
    }

    private String getClauseName() {
        String clauseName = "";
        String tableName = queryObject.getTableName();
        Pattern columnsName = Pattern.compile(
                ".*FROM " + tableName + " (\\w+) (?:.*=.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(queryString);
        if (matcher.matches()) {
            clauseName = matcher.group(1);
        }
        return clauseName;
    }

    private String getClauseCondition() {
        String clauseCondition = "";
        String clauseName = queryObject.getClauseName();
        Pattern columnsName = Pattern.compile(
                ".*" + clauseName + " (\\w+)?=.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(queryString);
        if (matcher.matches()) {
            clauseCondition = matcher.group(1);
        }
        return clauseCondition;
    }

    private String getClauseValue() {
        String clauseValue = "";
        Pattern columnsName = Pattern.compile(
                ".*=?'(.*)'.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = columnsName.matcher(queryString);
        if (matcher.matches()) {
            clauseValue = matcher.group(1);
        }
        return clauseValue;
    }

}
