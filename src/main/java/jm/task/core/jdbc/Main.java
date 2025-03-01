package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 30);
        userService.saveUser("Petr", "Petrov", (byte) 25);
        userService.saveUser("Sergey", "Sergeev", (byte) 40);
        userService.saveUser("Sidor", "Sidorov", (byte) 22);
        userService.saveUser("Vasiliy", "Vasiliev", (byte) 37);

        userService.getAllUsers().forEach(System.out::println);

       userService.removeUserById(10);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
