package org.example;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class Databases {

/*    private static final String HOST = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";*/

    private static String url = null;
    private static String username = null;
    private static String password = null;
    private Connection connection;
    Database database;

    public Databases() {

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\bareisha.darya\\IdeaProjects\\DB\\src\\test\\resources\\config\\application.yaml")) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(fileInputStream);
            username = (String) data.get("username");
            password = (String) data.get("password");
            url = (String) data.get("url");
            /*database = yaml.load(fileInputStream);
            password = database.getPassword();
            username = database.getUsername();
            url = database.getUrl();*/
        } catch (IOException e) {
            System.out.println("Couldn't find .yaml file");
        }

        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
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

    public static void main(String[] args) {
        Databases databases = new Databases();
        databases.getConnection();
    }
}

