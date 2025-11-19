package com.example.crudalimentosmedicamentos.crud;

import com.example.crudalimentosmedicamentos.crud.util.DbUtil;
import javafx.application.Application;

import static javafx.application.Application.*;

public class MainApp {
    public static void main(String[] args) {
        launch(DbUtil.class, args);
    }

    private static void launch(Class<DbUtil> dbUtilClass, String[] args) {

    }
}
