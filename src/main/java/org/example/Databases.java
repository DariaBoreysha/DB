package org.example;


import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.*;
import java.util.Map;

public class Databases {
    private static final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";

   private Connection connection;
    public Databases() {

        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось найти файл драйвера");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}

