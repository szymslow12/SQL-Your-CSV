package com.codecool.SQLYourCSV.controller;

import com.codecool.SQLYourCSV.model.query.QueryParser;
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
    public String content() {
        return "sheetQl";
    }

    @PostMapping("/sheetql")
    public String result(Model model, @RequestBody String query) {
//        String name = request.getParameter("name");

        model.addAttribute("table", service.createTableFromQuery(QueryParser.parse(query)));
        return "sheetQl";
    }
}
