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
    void testQueryWhenNoSemicolonAtEndExpectIllegalExcepion(){
        String statement = "Select table from table_name where cond='result'";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testMultipleColumnWithoutCommaExpectIllegalException(){
        String statement = "Select column1 column2 from table_name where cond='result'";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testQueryWithTooMuchSpacesExpectedSuccess() {
        String statement = "Select * from          table_name    where cond   =    'result';";
        String expected = "Query{statement='SELECT', columns=[*], tableName='table_name', clauseName='where'," +
                " clauseCondition='cond', clauseValue='result'}";
        String result = QueryParser.parse(statement).toString();
        Assert.assertEquals(expected, result);
    }

    @Test
    void testQueryWithTooMuchColumnsInQueryExpectedIllegalExpcetion() {
        String statement = "Select column1 from table_name table2 where cond='result';";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testQueryWitchoutCommasInClauseValuesExpectedIllegalExcpetion() {
        String statement = "Select column1 from table_name where cond=result;";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testQueryWithSemicolonInQueryExpectedIllegalExcpetion() {
        String statement = "Select column1 from ;table_name where cond='result';";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }

    @Test
    void testQueryWithTwoSelectStatementExpectedIllegalExcpetion() {
        String statement = "Select column1 from ;table_name where select='result';";
        assertThrows(IllegalArgumentException.class,
                () ->{
                    QueryParser.parse(statement);
                });
    }


    @Test
    void testQueryWithMoreThanTwoColumnsSuccess() {
        String statment = "Select col1, col2, col3 From table_name;";

        String expected = "Query{statement='SELECT', columns=[col1, col2, col3], tableName='table_name', clauseName=''," +
                " clauseCondition='', clauseValue=''}";

        String actual = QueryParser.parse(statment).toString();

        Assert.assertEquals(expected, actual);
    }
}