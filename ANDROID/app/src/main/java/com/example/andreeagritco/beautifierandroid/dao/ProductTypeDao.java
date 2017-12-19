package com.example.andreeagritco.beautifierandroid.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductType;

import java.util.List;

/**
 * Created by Andreea Gritco on 04-Dec-17.
 */

@Dao
public interface ProductTypeDao {

    @Query("SELECT * FROM product_types")
    List<ProductType> getAllProductTypes();

    @Query("SELECT * FROM product_types WHERE product_description LIKE :description")
    ProductType findByDescription(String description);

    @Insert
    void insertAll(List<ProductType> productType);

    @Delete
    void delete(ProductType productType);
}
