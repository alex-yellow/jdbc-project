package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50)," +
                "lastname VARCHAR(50)," +
                "age INTEGER);";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.executeUpdate();
            System.out.println("Таблица users создана.");
        }catch (SQLException e){
            System.out.println("Ошибка при создании таблицы users.");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.executeUpdate();
            System.out.println("Таблица users удалена.");
        }catch (SQLException e){
            System.out.println("Ошибка при удалении таблицы users.");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES(?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }catch (SQLException e){
            System.out.println("Ошибка при добавлении пользователя.");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            int rows = statement.executeUpdate();
            if(rows > 0){
                System.out.println("Пользователь с Id " + id + " удален.");
            }
            else{
                System.out.println("Пользователь с Id " + id + " не найден.");
            }
        }catch (SQLException e){
            System.out.println("Ошибка при удалении пользователя.");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            System.out.println("Получен список всех пользователей.");
        }catch (SQLException e){
            System.out.println("Ошибка при получении списка пользователей.");
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.executeUpdate();
            System.out.println("Таблица users очищена.");
        }catch (SQLException e){
            System.out.println("Ошибка при очистке таблицы users.");
            e.printStackTrace();
        }
    }
}
