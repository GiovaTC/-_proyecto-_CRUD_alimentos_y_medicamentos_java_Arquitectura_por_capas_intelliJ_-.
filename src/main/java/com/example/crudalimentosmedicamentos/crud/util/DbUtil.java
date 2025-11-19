package com.example.crudalimentosmedicamentos.crud.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DbUtil {
    private static final String PROPS = "/db.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream is = DbUtil.class.getResourceAsStream(PROPS)) {
            Properties p = new Properties();
            p.load(is);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
            // driver class for oracle : optional since JDBC 4.0 auto-registers
            Class.forName(p.getProperty("db.driver"));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
