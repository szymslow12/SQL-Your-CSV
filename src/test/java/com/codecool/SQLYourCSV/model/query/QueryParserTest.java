package com.codecool.SQLYourCSV.model.query;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryParserTest {

    @Test
    void testQueryWitchoutWhereExpectedSuccess() {
        String statement = "Select * from table_name;";
        String expected = "Query{statement='SELECT', columns=[*], tableName='table_name', clauseName=''," +
                " clauseCondition='', clauseValue=''}";
        String result = QueryParser.parse(statement).toString();
        Assert.assertEquals(expected, result);
    }

    @Test
    void testQueryWithWhereExpectedSuccess() {
        String statement = "Select * from table_name where cond='result';";
        String expected = "Query{statement='SELECT', columns=[*], tableName='table_name', clauseName='where'," +
                " clauseCondition='cond', clauseValue='result'}";
        String result = QueryParser.parse(statement).toString();
        Assert.assertEquals(expected, result);
    }

    @Test
    void testQueryWhenNoSemicolonAtEnd(){
        String statement = "Select * from table_name where cond='result'";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testQueryWhenTooMuchSemicolonAtEnd(){
        String statement = "Select * from table_name where cond='result';;";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

}