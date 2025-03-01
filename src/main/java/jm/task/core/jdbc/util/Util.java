package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5432/usersdb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("JDBC-драйвер PostgreSQL загружен!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки драйвера", e);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Соединение с PostgreSQL установлено!");
            return connection;
        } catch (SQLException e) {
            System.out.println("Ошибка подключения к БД!");
            e.printStackTrace();
            return null;
        }
    }
}
