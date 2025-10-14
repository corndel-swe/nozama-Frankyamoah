package com.corndel.nozama.repositories;

import com.corndel.nozama.DB;
import com.corndel.nozama.models.Product;

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

    public static void main(String[] args) {
        try {
            List<Product> result = ProductRepository.findAll();
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


