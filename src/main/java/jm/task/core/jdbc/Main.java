package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Valeriia", "Laine", (byte) 21);
        userService.saveUser("Jane", "Doe", (byte) 41);
        userService.saveUser("Edson", "Fransisco", (byte) 34);
        userService.saveUser("John", "Smith", (byte) 25);

        List<User> userList = userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
