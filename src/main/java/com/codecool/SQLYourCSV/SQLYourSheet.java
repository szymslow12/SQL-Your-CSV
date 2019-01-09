package com.codecool.SQLYourCSV;

import com.codecool.SQLYourCSV.controller.AppController;
import com.codecool.SQLYourCSV.model.service.GoogleAuthorizeUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SQLYourSheet {
    public static void main(String[] args) {
        try {
            GoogleAuthorizeUtil.authorize();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "META-INF/SQLYourCSV.xml");
        ((AppController) context.getBean("appController")).run();
    }
}
