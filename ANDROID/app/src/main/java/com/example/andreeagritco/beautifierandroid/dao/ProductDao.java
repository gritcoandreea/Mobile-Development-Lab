package com.example.andreeagritco.beautifierandroid.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.andreeagritco.beautifierandroid.domain.Product;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Andreea Gritco on 04-Dec-17.
 */

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    List<Product> getAll();

    @Insert
    void insertAll(List<Product> products);

    @Insert
    void insertProduct(Product product);

    @Delete
    void delete(Product product);

    @Query("SELECT * FROM products WHERE description LIKE :description")
    Product findByDescription(String description);

    @Query("SELECT * FROM products WHERE id LIKE :id")
    Product findById(int id);

    @Update
    void updateProducts(Product... products);

    @Query("SELECT purchased_date FROM products")
    Cursor getAllDates();
}
