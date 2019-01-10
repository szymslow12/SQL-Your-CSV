package com.codecool.SQLYourCSV;

import com.codecool.SQLYourCSV.controller.AppController;
import com.codecool.SQLYourCSV.model.service.GoogleAuthorizeUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SQLYourCSV {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/SheetQL.xml");
        ((AppController) context.getBean("appController")).run();
    }
}
