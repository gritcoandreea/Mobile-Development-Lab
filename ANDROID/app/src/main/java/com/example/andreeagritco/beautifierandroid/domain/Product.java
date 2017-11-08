package com.example.andreeagritco.beautifierandroid.domain;

import java.io.Serializable;

/**
 * Created by Andreea Gritco on 08-Nov-17.
 */

public class Product implements Serializable {

    private int id;
    private String description;
    private String productType;
    private int quantity;
    private int price;
    private int imagePath;
    private String brand;

    public Product(int id, String description, String productType, int quantity, int price,String brand,int imagePath) {
        this.id = id;
        this.description = description;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
