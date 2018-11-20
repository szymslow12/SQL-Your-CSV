package com.codecool.SQLYourCSV;

import com.codecool.SQLYourCSV.controller.AppController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SQLYourCSV {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/SQLYourCSV.xml");
        ((AppController) context.getBean("appController")).run();
    }
}
