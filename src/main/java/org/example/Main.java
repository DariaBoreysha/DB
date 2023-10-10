package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Main {

    static Databases db = new Databases();

    private Statement st = db.getConnection().createStatement();

    public Main() throws SQLException {
    }


    public String getLastUserName() {
        String selectLast = "SELECT * FROM users ORDER BY id_user DESC LIMIT 1";
        String nameRes = null;
        try {
            ResultSet getLast = st.executeQuery(selectLast);
            getLast.next();
            nameRes = getLast.getString(2);

        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        return nameRes;
    }

    public String getLastUserEmail() {
        String selectLast = "SELECT * FROM users ORDER BY id_user DESC LIMIT 1";
        String emailRes = null;
        try {
            ResultSet getLast = st.executeQuery(selectLast);
            getLast.next();
            emailRes = getLast.getString(3);

        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        return emailRes;
    }

    public String getLastUserPass() {
        String selectLast = "SELECT * FROM users ORDER BY id_user DESC LIMIT 1";
        String passRes = null;
        try {
            ResultSet getLast = st.executeQuery(selectLast);
            getLast.next();
            passRes = getLast.getString(4);

        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        return passRes;
    }

    public void insertNewUser(String name, String email, String password) {

        String insertNewUser = String.format("INSERT INTO users (username, email,password) VALUES ('%s', '%s','%s')", name, email, password);

        try {
            st.execute(insertNewUser);
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
    }

    public void updatePassword(String password, int id) {

        String updatePassword = String.format("update users set password = '%s' where id_user = %d", password, id);

        try {
            st.execute(updatePassword);
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
    }

    public String selectUserPassword(int id) {
        String selectPassword = String.format("select password from users where id_user = %s", id);
        String pass = null;
        try {
            ResultSet resultSet = st.executeQuery(selectPassword);
            resultSet.next();
            pass = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        return pass;
    }

    public void getFullTable() {
        String selectAll = "SELECT * FROM users";
        try {
            ResultSet resultSet = st.executeQuery(selectAll);

            while (resultSet.next()) {
                User user = new User();
                user.setId_user(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }

    }

    public int getNumberOfRows() {
        String getRowsCount = "select count(1) from users ";
        int rowsCount = 0;
        try {
            ResultSet resultSet = st.executeQuery(getRowsCount);
            resultSet.next();
            rowsCount = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        return rowsCount;
    }

    public void deleteLastRow() {
        String deleteLastRow = "delete FROM users where id_user in (SELECT id_user FROM users ORDER BY id_user DESC LIMIT 1)";
        try {
            st.execute(deleteLastRow);
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
    }

    /*public static void main(String[] args) {

        String selectPassword = String.format("select password from users where id_user = %s", 1);
        String pass = null;
        try {
            ResultSet resultSet = st.executeQuery(selectPassword);
            resultSet.next();
            pass = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println("Не найден файл драйвера");
        }
        System.out.println(pass);
    }*/
}

