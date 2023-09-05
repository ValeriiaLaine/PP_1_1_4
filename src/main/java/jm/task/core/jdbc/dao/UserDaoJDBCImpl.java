package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                "lastName VARCHAR(255)," +
                "age TINYINT UNSIGNED)";
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table was successfully created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table was successfully dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setInt(3, age);
                preparedStatement.executeUpdate();
                System.out.println("User : " + name + " " + lastName + " " + age + " was saved");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, id);
                int deleted = preparedStatement.executeUpdate();
                if (deleted > 0) {
                    System.out.println("User with ID " + id + " was deleted from the database");
                } else {
                    System.out.println("User with ID " + id + " was not found in the database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<User> getAllUsers() {
        int userCounter = 0;
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");
                    User user = new User(name, lastName, age);
                    user.setId(id);
                    userList.add(user);
                    System.out.println(user.toString());
                    userCounter++;
                }
            }
            if (userCounter > 0) {
                System.out.println("Table was successfully printed");
            } else {
                System.out.println("There are no available users");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        String query = "DELETE FROM users";
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                int deleted = preparedStatement.executeUpdate();
                if (deleted > 0) {
                    System.out.println("All users was deleted from database");
                } else {
                    System.out.println("Nobody was deleted from database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
