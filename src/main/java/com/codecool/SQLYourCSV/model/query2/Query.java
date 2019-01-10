package com.codecool.SQLYourCSV.model.query2;

import java.util.Arrays;

public class Query {

    private String statement;

    private String[] columns;

    private String tableName;

    private String clauseName;

    private String clauseValue;

    public Query() {
    }

    public Query(String statement, String[] columns, String tableName, String clauseName, String clauseValue) {
        this.statement = statement;
        this.columns = columns;
        this.tableName = tableName;
        this.clauseName = clauseName;
        this.clauseValue = clauseValue;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClauseName() {
        return clauseName;
    }

    public void setClauseName(String clauseName) {
        this.clauseName = clauseName;
    }

    public String getClauseValue() {
        return clauseValue;
    }

    public void setClauseValue(String clauseValue) {
        this.clauseValue = clauseValue;
    }

    @Override
    public String toString() {
        return "Query{" +
                "statement='" + statement + '\'' +
                ", columns=" + Arrays.toString(columns) +
                ", tableName='" + tableName + '\'' +
                ", clauseName='" + clauseName + '\'' +
                ", clauseValue='" + clauseValue + '\'' +
                '}';
    }
}
