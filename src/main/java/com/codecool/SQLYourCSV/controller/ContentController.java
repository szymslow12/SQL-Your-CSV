package com.codecool.SQLYourCSV.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ContentController {

    @GetMapping
    public String contentForm() {
        return "sheetQl";
    }

    @PostMapping
    public String resultForm(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");

        return "sheetQl";
    }
}
