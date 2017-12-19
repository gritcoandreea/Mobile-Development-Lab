package com.example.andreeagritco.beautifierandroid.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Gritco on 08-Nov-17.
 */

@Entity(tableName = "brand_types")
public class BrandType {

//    @PrimaryKey(autoGenerate = true)
//    private int brand_id;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "brand_name")
    private String brand_name;

//    @Ignore
//    public BrandType(int brand_id, String brand_name) {
//        this.brand_id = brand_id;
//        this.brand_name = brand_name;
//    }

    public BrandType(String brand_name) {
        this.brand_name = brand_name;
    }


//    public int getBrand_id() {
//        return brand_id;
//    }

//    public void setBrand_id(int brand_id) {
//        this.brand_id = brand_id;
//    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;

    }

    public String toString(){
        return brand_name;
    }

    //    public static final String TOO_FACED = "Too Faced";
//    public static final String RIMMEL_LONDON = "Rimmel London";
//    public static final String ANASTASIA_BEVERLY_HILLS = "Anastasia Beverly Hills";
//    public static final String MELKIOR = "Melkior";
//    public static final String MAC = "MAC";
//    public static final String ESSENCE = "essence";
//    public static final String MAYBELLINE = "Maybelline New York";
//    public static final String BOURJOIS = "Bourjois";
//    public static final String URBAN_DECAY = "Urban Decay";
//    public static final String NYX = "NYX";
//
//    public static List<String> returnBrands(){
//        List<String> list = new ArrayList<>();
//        list.add(TOO_FACED);
//        list.add(RIMMEL_LONDON);
//        list.add(ANASTASIA_BEVERLY_HILLS);
//        list.add(MELKIOR);
//        list.add(MAC);
//        list.add(ESSENCE);
//        list.add(MAYBELLINE);
//        list.add(BOURJOIS);
//        list.add(URBAN_DECAY);
//        list.add(NYX);
//        return list;
//    }
}
