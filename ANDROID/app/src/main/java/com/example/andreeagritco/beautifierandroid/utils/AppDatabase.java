package com.example.andreeagritco.beautifierandroid.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.andreeagritco.beautifierandroid.dao.BrandTypeDao;
import com.example.andreeagritco.beautifierandroid.dao.ProductDao;
import com.example.andreeagritco.beautifierandroid.dao.ProductTypeDao;
import com.example.andreeagritco.beautifierandroid.domain.BrandType;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductType;

/**
 * Created by Andreea Gritco on 04-Dec-17.
 */

@Database(entities = {Product.class, ProductType.class, BrandType.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ProductDao productDao();

    public abstract ProductTypeDao productTypeDao();

    public abstract BrandTypeDao brandTypeDao();

    public static AppDatabase getAppDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, "beautifier2").build();
        }
        return INSTANCE;
    }
}
