package com.example.andreeagritco.beautifierandroid.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreea Gritco on 08-Nov-17.
 */

public class BrandTypes {

    public static final String TOO_FACED = "Too Faced";
    public static final String RIMMEL_LONDON = "Rimmel London";
    public static final String ANASTASIA_BEVERLY_HILLS = "Anastasia Beverly Hills";
    public static final String MELKIOR = "Melkior";
    public static final String MAC = "MAC";
    public static final String ESSENCE = "essence";
    public static final String MAYBELLINE = "Maybelline New York";
    public static final String BOURJOIS = "Bourjois";
    public static final String URBAN_DECAY = "Urban Decay";
    public static final String NYX = "NYX";

    public static List<String> returnBrands(){
        List<String> list = new ArrayList<>();
        list.add(TOO_FACED);
        list.add(RIMMEL_LONDON);
        list.add(ANASTASIA_BEVERLY_HILLS);
        list.add(MELKIOR);
        list.add(MAC);
        list.add(ESSENCE);
        list.add(MAYBELLINE);
        list.add(BOURJOIS);
        list.add(URBAN_DECAY);
        list.add(NYX);
        return list;
    }
}
