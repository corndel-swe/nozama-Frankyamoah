package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<User> findAll() throws SQLException {
        var query = "SELECT id, username, firstName, lastName, email, avatar, password FROM users";

        try (var con = DB.getConnection();
             var stmt = con.createStatement();
             var rs = stmt.executeQuery(query);) {

            var users = new ArrayList<User>();
            while (rs.next()) {
                var id = rs.getInt("id");
                var username = rs.getString("username");
                var firstName = rs.getString("firstName");
                var lastName = rs.getString("lastName");
                var email = rs.getString("email");
                var avatar = rs.getString("avatar");
                var password = rs.getString("password");

                users.add(new User(id, username, firstName, lastName, email, avatar, password));
            }

            return users;
        }
    }

    public static User findById(int id) throws SQLException {
        // TODO: finish this method
        var query = "SELECT id, username, firstName, lastName, email, avatar, password FROM users WHERE id =" + id;

        try (
                var connection = DB.getConnection();
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery(query);) {


            var username = resultSet.getString("username");
            var firstName = resultSet.getString("firstName");
            var lastName = resultSet.getString("lastName");
            var email = resultSet.getString("email");
            var avatar = resultSet.getString("avatar");
            var password = resultSet.getString("password");
            var foundUser = (new User(username, firstName, lastName, email, avatar, password));


            return foundUser;
        }
    }

    public static User createUser(User user) throws SQLException {

//    var query = String.format("INSERT INTO users (username, firstName, lastName, email, avatar) VALUES (%s, %s, %s, %s, %s)",
//            user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar());

        String query = "INSERT INTO users(username, firstName, lastName, email, avatar, password) VALUES(?, ?, ?, ?, ?, ?) RETURNING *";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAvatar());
            preparedStatement.setString(6, user.getPassword());

            try (var resultSet = preparedStatement.executeQuery()) {

                if (!resultSet.next()) {
                    return null;
                }

                int id = resultSet.getInt("id");
//                String username = resultSet.getString("username");
//                String firstName = resultSet.getString("firstName");
//                String lastName = resultSet.getString("lastName");
//                String email = resultSet.getString("email");
//                String avatar = resultSet.getString("avatar");
//                String password = resultSet.getString("password")

                user.setId(id);
                user.setPassword(null);

            }


            System.out.println("New user created");

            return user;

        }
    }

    public static void deleteById(int id) throws SQLException {
        var query = "DELETE FROM users WHERE id = ?";

        try (
                var connection = DB.getConnection();
                var statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();

        }
    }

    public static void main(String[] args) throws SQLException {

//        System.out.println(findAll());

    }
}

