package com.example.andreeagritco.beautifierandroid.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.andreeagritco.beautifierandroid.domain.BrandType;

import java.util.List;

/**
 * Created by Andreea Gritco on 04-Dec-17.
 */

@Dao
public interface BrandTypeDao {

    @Query("SELECT * FROM brand_types")
    List<BrandType> getAllBrandTypes();

    @Query("SELECT * FROM brand_types WHERE brand_name LIKE :description")
    BrandType findByDescription(String description);


    @Insert
    void insertAll(List<BrandType> brandType);

    @Delete
    void delete(BrandType brandType);
}
