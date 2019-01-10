package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.datastructure.Table;
import com.codecool.SQLYourCSV.model.query2.QueryParser;
import com.codecool.SQLYourCSV.model.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ContentController {

    @Autowired
    private TableService service;

    @GetMapping("/sheetql")
    public String content(Model model) {
        model.addAttribute("isSentQuery", false);
        return "sheetQl";
    }

    @PostMapping("/sheetql")
    public String result(Model model, @RequestBody String query) {
        Table table = service.createTableFromQuery(QueryParser.parse(query));
        model.addAttribute("isSentQuery", true);
        model.addAttribute("table", table);
        return "sheetQl";
    }
}
