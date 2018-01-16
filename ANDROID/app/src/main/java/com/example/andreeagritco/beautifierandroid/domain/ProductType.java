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

public class ProductType {

    private String product_description;


    public ProductType(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;

    }

    public String toString() {
        return product_description;
    }

    public static final String FOND_DE_TEN = "Fond de ten";
    public static final String PUDRA_COMPACTA = "Pudra compacta";
    public static final String PUDRA_MINEARALA = "Pudra minerala";
    public static final String CONCEALER = "Concealer";
    public static final String PUDRA_CONTUR = "Pudra de conturare";
    public static final String ILUMINATOR = "Iluminator";
    public static final String BLUSH = "Blush";
    public static final String CREION_DE_BUZE = "Creion de buze";
    public static final String RUJ = "Ruj";
    public static final String FARD_DE_OCHI = "Fard de ochi";
    public static final String CREION_DE_OCHI = "Creion de ochi";
    public static final String RIMEL = "Rimel";
    public static final String CREION_DE_SPRANCENE = "Creion de sprancene";

    public static List<String> returnTypes() {
        List<String> list = new ArrayList<>();
        list.add(FOND_DE_TEN);
        list.add(PUDRA_COMPACTA);
        list.add(PUDRA_MINEARALA);
        list.add(CONCEALER);
        list.add(PUDRA_CONTUR);
        list.add(ILUMINATOR);
        list.add(BLUSH);
        list.add(CREION_DE_BUZE);
        list.add(RUJ);
        list.add(FARD_DE_OCHI);
        list.add(CREION_DE_OCHI);
        list.add(RIMEL);
        list.add(CREION_DE_SPRANCENE);
        return list;
    }


}
