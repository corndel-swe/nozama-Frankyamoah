package com.corndel.nozama.models;

// Creating product instance
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private String imageURL;

    // Initialize an instance of Product
    public Product(Integer id, String name, String description, Integer price, Integer stockQuantity, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageURL = imageURL;
    }
    // Getter for Id
    public Integer getId() {
        return id;
    }
    // Getter for name
    public String getName() {
        return name;
    }
    // Setter for product name
    public void setName(String name) {
        this.name = name;
    }
    // Getter for description
    public String getDescription() {
        return description;
    }
    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for price
    public Integer getPrice() {
        return price;
    }
    // Setter for price
    public void setPrice(Integer price) {
        this.price = price;
    }
    // Getter for stock quantity
    public Integer getStockQuantity() {
        return stockQuantity;
    }
    //Setter for stock quantity
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    // Getter for imageURL
    public String getImageURL() {
        return imageURL;
    }
    // Setter for imageURL
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}


