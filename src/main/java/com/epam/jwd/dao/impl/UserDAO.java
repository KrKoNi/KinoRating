package com.epam.jwd.dao.impl;

import com.epam.jwd.connect.impl.BasicConnectionPool;
import com.epam.jwd.dao.DataAccessObject;
import com.epam.jwd.domain.Role;
import com.epam.jwd.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO implements DataAccessObject<User> {

    private static final UserDAO INSTANCE = new UserDAO();

    private UserDAO() {}

    public static UserDAO getInstance() {
        return INSTANCE;
    }

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private LocalDate birthDate;
    private String email;
    private LocalDate registrationDate;
    private int roleId;

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
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                LocalDate registrationDate = resultSet.getDate("registration_date").toLocalDate();
                int roleId = resultSet.getInt("role_id");

                User user = new User(id, login, password, firstName, lastName, birthDate, email, registrationDate, Role.getById(roleId));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public List<User> readWithOffset(int offset, int num) {
        return null;
    }

    @Override
    public User findById(int id) {
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.users where id = ? limit 1"
        )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                setParams(resultSet);
            }

            user = new User(id, login, password, firstName, lastName, birthDate, email, registrationDate, Role.getById(roleId));
            initUserRates(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
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
                setParams(resultSet);
                user = new User(id, login, password, firstName, lastName, birthDate, email, registrationDate, Role.getById(roleId));
                initUserRates(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    public void initUserRates(User user) {
        Map<Integer, Byte> rates = user.getRates();
        Connection connection = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM kinorating.kino_ratings where user_id = ?;"
        )) {
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int showId = resultSet.getInt("kino_id");
                byte rate = (byte) resultSet.getInt("kino_rating");
                rates.put(showId, rate);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private void setParams(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        firstName = resultSet.getString("first_name");
        lastName = resultSet.getString("last_name");
        login = resultSet.getString("login");
        password = resultSet.getString("password");
        birthDate = resultSet.getDate("birth_date").toLocalDate();
        email = resultSet.getString("email");
        registrationDate = resultSet.getDate("registration_date").toLocalDate();
        roleId = resultSet.getInt("role_id");
    }

}
