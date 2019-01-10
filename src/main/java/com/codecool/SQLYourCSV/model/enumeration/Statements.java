package com.codecool.SQLYourCSV.model.enumeration;

public enum Statements {
    SELECT("SELECT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    INSERT_INTO("INSERT INTO"),
    CREATE_DATABASE("CREATE DATABASE"),
    ALTER_DATABASE("ALTER DATABASE"),
    CREATE_TABLE("CREATE TABLE"),
    ALTER_TABLE("ALTER TABLE"),
    DROP_TABLE("DROP TABLE"),
    CREATE_INDEX("CREATE INDEX"),
    DROP_INDEX("DROP INDEX");

    private String statement;

    Statements(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
