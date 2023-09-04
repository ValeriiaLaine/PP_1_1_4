package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();

        userDao.saveUser("Valeriia", "Laine", (byte) 21);
        userDao.saveUser("Jane", "Doe", (byte) 41);
        userDao.saveUser("Edson", "Fransisco", (byte) 34);
        userDao.saveUser("John", "Smith", (byte) 25);

        List<User> userList = userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
