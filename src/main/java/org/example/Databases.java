package org.example;


import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Databases {
    InputStream yamlDoc = getClass().getClassLoader().getResourceAsStream("application.yaml");
    Yaml yaml = new Yaml();
    Map data = yaml.load(yamlDoc);

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

