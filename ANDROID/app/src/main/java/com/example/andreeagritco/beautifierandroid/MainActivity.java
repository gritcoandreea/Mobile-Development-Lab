package com.example.andreeagritco.beautifierandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andreeagritco.beautifierandroid.domain.BrandTypes;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductTypes;
import com.example.andreeagritco.beautifierandroid.utils.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final  String TAG=MainActivity.class.getName();
    private static final int SECOND_ACTIVITY_RESULT_CODE = 0;
     List<Product> list = new ArrayList<>() ;
    ProductListAdapter adapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"IN ON CREATE FMM");
        createList();

        listView = (ListView) findViewById(R.id.productList);
        adapter = new ProductListAdapter(list,getLayoutInflater());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("product", list.get(position));
                startActivityForResult(intent, SECOND_ACTIVITY_RESULT_CODE);

            }

        });
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {

                // get String data from Intent
                Product p = (Product) data.getSerializableExtra("result");

                for(Product pr : list){
                    if(pr.getId()== p.getId()){
                        pr.setDescription(p.getDescription());
                        pr.setProductType(p.getProductType());
                        pr.setQuantity(p.getQuantity());
                        pr.setPrice(p.getPrice());
                        pr.setBrand(p.getBrand());
                    }
                }


            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


    public  void createList(){
        list.add(new Product(1,"Fond de ten Born This Way", ProductTypes.FOND_DE_TEN,2,180, BrandTypes.TOO_FACED,R.drawable.fond_de_ten));
        list.add(new Product(2,"Highlighter", ProductTypes.ILUMINATOR,1,200, BrandTypes.ANASTASIA_BEVERLY_HILLS,R.drawable.highlighter));
        list.add(new Product(3,"Pudra compacta",ProductTypes.PUDRA_COMPACTA,1,35,BrandTypes.RIMMEL_LONDON,R.drawable.pudra));
        list.add(new Product(4,"Rimel",ProductTypes.RIMEL,1,25,BrandTypes.MELKIOR,R.drawable.rimel));
        list.add(new Product(5,"Ruj baton",ProductTypes.RUJ,1,90,BrandTypes.MAC,R.drawable.ruj));
        list.add(new Product(6,"Creion de buze nuanta 06 Satin Mauve",ProductTypes.CREION_DE_BUZE,1,15,BrandTypes.ESSENCE,R.drawable.creion_de_buze));
        list.add(new Product(7,"Fond de ten nuanta 02",ProductTypes.FOND_DE_TEN,1,50,BrandTypes.BOURJOIS,R.drawable.fond_de_ten_2));
        list.add(new Product(8,"Paleta de farduri Naked 3",ProductTypes.FARD_DE_OCHI,1,230,BrandTypes.URBAN_DECAY,R.drawable.naked_palette));
        list.add(new Product(9,"Rimel Colossal",ProductTypes.RIMEL,3,30,BrandTypes.MAYBELLINE,R.drawable.rimel_colossal));
        list.add(new Product(10,"Ruj Soft Matte nuanta Copenhagen",ProductTypes.RUJ,1,70,BrandTypes.NYX,R.drawable.ruj_copenhagen));
        list.add(new Product(11,"Creion de sprancene nuanta Dark Brown",ProductTypes.CREION_DE_SPRANCENE,1,11,BrandTypes.ESSENCE,R.drawable.sprancene_darkbrown));


    }

}
