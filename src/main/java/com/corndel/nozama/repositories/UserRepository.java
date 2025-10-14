package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public static List<User> findAll() throws SQLException {
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users";

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

        users.add(new User(id, username, firstName, lastName, email, avatar));
      }

      return users;
    }
  }

  public static User findById(int id) throws SQLException {
    // TODO: finish this method
    var query = "SELECT id, username, firstName, lastName, email, avatar FROM users WHERE id =" + id;

    try (
            var connection = DB.getConnection();
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);) {


      var username = resultSet.getString("username");
      var firstName = resultSet.getString("firstName");
      var lastName = resultSet.getString("lastName");
      var email = resultSet.getString("email");
      var avatar = resultSet.getString("avatar");
      var foundUser = (new User(id, username, firstName, lastName, email, avatar));


      return foundUser;
    }
  }

  public static User createUser(User user) throws SQLException {

    var query = String.format("INSERT INTO users (username, firstName, lastName, email, avatar) VALUES (%s, %s, %s, %s, %s)",
            user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAvatar());

    try (
            var connection = DB.getConnection();
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);) {


        resultSet.getString("username");
        resultSet.getString("firstName");
        resultSet.getString("lastName");
        resultSet.getString("email");
        resultSet.getString("avatar");


      System.out.println("New user created");

      return user;

    }
  }

  public static void main(String[] args) throws SQLException {
    User newUser = new User("jackP", "J", "Potts",
            "jpotts@gmail.com", "https//kmart");

    createUser(newUser);

    System.out.println(newUser);

  }
}

