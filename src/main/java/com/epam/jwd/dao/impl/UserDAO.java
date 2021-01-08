package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DataAccessObject<User> {

    private static final UserDAO INSTANCE = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return INSTANCE;
    }


    @Override
    public List<User> readAll() {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.users natural join kinorating.user_role"
        )) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                LocalDate registrationDate = resultSet.getDate("registration_date").toLocalDate();
                LocalDateTime lastLogin = LocalDateTime.now();//LocalDateTime.parse(resultSet.getString("last_login"));
                int roleId = resultSet.getInt("role_id");
                String roleName = resultSet.getString("role_name");


                //User user = new User(id, login, password, firstName, lastName, birthDate, registrationDate, role);
                //users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }
    public User findByLoginAndPassword(String login, String password) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.users where login = ? and password = ?"
        )) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                String email = resultSet.getString("email");
                LocalDate registrationDate = resultSet.getDate("registration_date").toLocalDate();
                LocalDateTime lastLogin = LocalDateTime.now();//LocalDateTime.parse(resultSet.getString("last_login"));
                int roleId = resultSet.getInt("role_id");


                user = new User(id, login, password, firstName, lastName, birthDate, email, registrationDate, Role.getById(roleId));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void insert(User user) throws SQLException {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO kinorating.users (first_name, last_name, birth_date, email, " +
                        "login, password, registration_date, role_id) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        ) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, Date.valueOf(user.getBirthDate()));
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());
            statement.setDate(7, Date.valueOf(user.getRegistrationDate()));
            statement.setInt(8, user.getRole().getId());

            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void update(User user) {

    }

}
