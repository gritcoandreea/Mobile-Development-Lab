package com.example.andreeagritco.beautifierandroid.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andreea Gritco on 08-Nov-17.
 */

public class Product implements Serializable {

    private String id;
    private String description;
    private String productType;
    private int quantity;
    private double price;
    private int imagePath;
    private String brand;
    private Date purchasedDate;
    private String userEmail;

    public Product(){

    }

    public Product(String description, String productType, int quantity, double price, String brand, int imagePath, Date purchasedDate,String userEmail) {
        this.description = description;
        this.productType = productType;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.imagePath = imagePath;
        this.purchasedDate = purchasedDate;
        this.userEmail = userEmail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Product: " + this.description + "\n Product type: " + this.productType + "\nQuantity: " + this.quantity + "\nPrice: " + this.price + "\nBrand: " + this.brand;
    }

    public static class Converters {

        @TypeConverter
        public Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }

    }
}



