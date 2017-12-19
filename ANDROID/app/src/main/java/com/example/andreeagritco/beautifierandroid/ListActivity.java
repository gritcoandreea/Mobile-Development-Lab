package com.example.andreeagritco.beautifierandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andreeagritco.beautifierandroid.domain.BrandType;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductType;
import com.example.andreeagritco.beautifierandroid.utils.AppDatabase;
import com.example.andreeagritco.beautifierandroid.utils.ProductListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ProductListAdapter adapter;

    ListView listView;

    List<Product> databaseProductList = new ArrayList<>();

    List<Product> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                createList();
                return null;
            }

        }.execute();

        listView = findViewById(R.id.productList);
        adapter = new ProductListAdapter(databaseProductList, getLayoutInflater());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("product", databaseProductList.get(position));
                startActivity(intent);

            }
        });


    }

    private AppDatabase getApplicationDatabase() {
        return AppDatabase.getAppDatabase(getApplicationContext());
    }

    public void createList() {
        databaseProductList.addAll(getApplicationDatabase().productDao().getAll());
        //  populateTestData();
    }

    private void populateTestData() {
        List<ProductType> listProductTypes = new ArrayList<>();
        listProductTypes.add(new ProductType("Fond de ten"));
        listProductTypes.add(new ProductType("Pudra compacta"));
        listProductTypes.add(new ProductType("Pudra minerala"));
        listProductTypes.add(new ProductType("Concealer"));
        listProductTypes.add(new ProductType("Pudra de conturare"));
        listProductTypes.add(new ProductType("Iluminator"));
        listProductTypes.add(new ProductType("Blush"));
        listProductTypes.add(new ProductType("Creion de buze"));
        listProductTypes.add(new ProductType("Ruj"));
        listProductTypes.add(new ProductType("Fard de ochi"));
        listProductTypes.add(new ProductType("Creion de ochi"));
        listProductTypes.add(new ProductType("Rimel"));
        listProductTypes.add(new ProductType("Creion de sprancene"));

        List<BrandType> brandTypes = new ArrayList<>();
        brandTypes.add(new BrandType("Too Faced"));
        brandTypes.add(new BrandType("Rimmel London"));
        brandTypes.add(new BrandType("Anastasia Beverly Hills"));
        brandTypes.add(new BrandType("Melkior"));
        brandTypes.add(new BrandType("MAC"));
        brandTypes.add(new BrandType("essence"));
        brandTypes.add(new BrandType("Maybelline New York"));
        brandTypes.add(new BrandType("Bourjois"));
        brandTypes.add(new BrandType("Urban Decay"));
        brandTypes.add(new BrandType("NYX"));


        list.add(new Product("Fond de ten Born This Way", "Fond de ten", 2, 180, "Too Faced", R.drawable.fond_de_ten, new Date(2017 - 1900, 4 - 1, 13)));
        list.add(new Product("Highlighter", "Iluminator", 1, 200, "Anastasia Beverly Hills", R.drawable.highlighter, new Date(2017 - 1900, 4 - 1, 27)));
        list.add(new Product("Pudra compacta", "Pudra compacta", 1, 35, "Rimmel London", R.drawable.pudra, new Date(2017 - 1900, 5 - 1, 5)));
        list.add(new Product("Rimel", "Rimel", 1, 25, "Melkior", R.drawable.rimel, new Date(2017 - 1900, 5 - 1, 29)));
        list.add(new Product("Ruj baton", "Ruj", 1, 90, "MAC", R.drawable.ruj, new Date(2017 - 1900, 6 - 1, 6)));
        list.add(new Product("Creion de buze nuanta 06 Satin Mauve", "Creion de buze", 1, 15, "essence", R.drawable.creion_de_buze, new Date(2017 - 1900, 6 - 1, 19)));
        list.add(new Product("Fond de ten nuanta 02", "Fond de ten", 1, 50, "Bourjois", R.drawable.fond_de_ten_2, new Date(2017 - 1900, 7 - 1, 20)));
        list.add(new Product("Paleta de farduri Naked 3", "Fard de ochi", 1, 230, "Urban Decay", R.drawable.naked_palette, new Date(2017 - 1900, 7 - 1, 16)));
        list.add(new Product("Rimel Colossal", "Rimel", 3, 30, "Maybelline New York", R.drawable.rimel_colossal, new Date(2017 - 1900, 8 - 1, 14)));
        list.add(new Product("Ruj Soft Matte nuanta Copenhagen", "Ruj", 1, 70, "NYX", R.drawable.ruj_copenhagen, new Date(2017 - 1900, 8 - 1, 24)));
        list.add(new Product("Creion de sprancene nuanta Dark Brown", "Creion de sprancene", 1, 11, "essence", R.drawable.sprancene_darkbrown, new Date(2017 - 1900, 9 - 1, 1)));


        //    getApplicationDatabase().productTypeDao().insertAll(listProductTypes);
        //   getApplicationDatabase().brandTypeDao().insertAll(brandTypes);
        for (Product p : databaseProductList) {
            getApplicationDatabase().productDao().delete(p);
        }

        getApplicationDatabase().productDao().insertAll(list);
    }


}
