package com.codecool.SQLYourCSV.model.query2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryParserTest {

    @Test
    void test() {
        QueryParser parser = new QueryParser("UPDATE INTO xD cos tam ja pierdole xD;");
        parser.queryParser();
        assertTrue(true);
    }

}