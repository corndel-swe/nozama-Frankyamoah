package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public static List<Product> findAll() throws SQLException {
        var query = "SELECT id, name, description, price, stockQuantity, imageURL FROM Products";
        try(
                var connection = DB.getConnection();
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery(query);
                ) {
            var Products = new ArrayList<Product>();
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var price = resultSet.getInt("price");
                var stockQuantity = resultSet.getInt("stockQuantity");
                var imageUrl = resultSet.getString("imageURL");
                Products.add(new Product(id, name, description, price, stockQuantity, imageUrl));
            }
            return Products;
        }
    }

    public static Product findByID(int id) throws SQLException {
        String query = "SELECT * FROM Products WHERE id = ?";

        try (
                Connection connection = DB.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, id);
                    try (ResultSet resulSet = statement.executeQuery()) {
                        if (!resulSet.next()) {
                            return null;
                        }
                        String name = resulSet.getString("name");
                        String description = resulSet.getString("description");
                        int price = resulSet.getInt("price");
                        int stockQuantity = resulSet.getInt("stockQuantity");
                        String imageURL = resulSet.getString("imageURL");
                        return new Product(id, name, description, price, stockQuantity, imageURL);
                    }
        }
    }
    /*
    public static User findById(int id) throws SQLException {
        // TODO: finish this method
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection con = DB.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {

                if (!resultSet.next()) {
                    return null;
                }
                int userId = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");

                return new User(userId, username, firstName, lastName, email, avatar);

     */

    public static void main(String[] args) {
        try {
            List<Product> result = ProductRepository.findAll();
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


