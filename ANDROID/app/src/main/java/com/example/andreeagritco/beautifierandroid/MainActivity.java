package com.example.andreeagritco.beautifierandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.andreeagritco.beautifierandroid.domain.BrandTypes;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductTypes;
import com.example.andreeagritco.beautifierandroid.utils.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Product> list = createList();

        ListView listView = (ListView) findViewById(R.id.productList);
        ProductListAdapter adapter = new ProductListAdapter(list,getLayoutInflater());
        listView.setAdapter(adapter);
    }


    public static List<Product> createList(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Fond de ten Born This Way", ProductTypes.FOND_DE_TEN,2,180, BrandTypes.TOO_FACED,R.drawable.fond_de_ten));
        productList.add(new Product(2,"Highlighter", ProductTypes.ILUMINATOR,1,200, BrandTypes.ANASTASIA_BEVERLY_HILLS,R.drawable.highlighter));
        productList.add(new Product(3,"Pudra compacta",ProductTypes.PUDRA_COMPACTA,1,35,BrandTypes.RIMMEL_LONDON,R.drawable.pudra));
        productList.add(new Product(4,"Rimel",ProductTypes.RIMEL,1,25,BrandTypes.MELKIOR,R.drawable.rimel));
        productList.add(new Product(5,"Ruj baton",ProductTypes.RUJ,1,90,BrandTypes.MAC,R.drawable.ruj));
        productList.add(new Product(6,"Creion de buze nuanta 06 Satin Mauve",ProductTypes.CREION_DE_BUZE,1,15,BrandTypes.ESSENCE,R.drawable.creion_de_buze));
        productList.add(new Product(7,"Fond de ten nuanta 02",ProductTypes.FOND_DE_TEN,1,50,BrandTypes.BOURJOIS,R.drawable.fond_de_ten_2));
        productList.add(new Product(8,"Paleta de farduri Naked 3",ProductTypes.FARD_DE_OCHI,1,230,BrandTypes.URBAN_DECAY,R.drawable.naked_palette));
        productList.add(new Product(9,"Rimel Colossal",ProductTypes.RIMEL,3,30,BrandTypes.MAYBELLINE,R.drawable.rimel_colossal));
        productList.add(new Product(10,"Ruj Soft Matte nuanta Copenhagen",ProductTypes.RUJ,1,70,BrandTypes.NYX,R.drawable.ruj_copenhagen));
        productList.add(new Product(11,"Creion de sprancene nuanta Dark Brown",ProductTypes.CREION_DE_SPRANCENE,1,11,BrandTypes.ESSENCE,R.drawable.sprancene_darkbrown));

        return productList;
    }

}
