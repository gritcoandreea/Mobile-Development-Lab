package com.example.andreeagritco.beautifierandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andreeagritco.beautifierandroid.domain.BrandType;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductType;
import com.example.andreeagritco.beautifierandroid.utils.ProductListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductsListActivity extends AppCompatActivity {

    //from lab5!!!!!!!!!1
    List<Product> list = new ArrayList<>();
    ////////////////////////////////////////

    ProductListAdapter adapter;
    ListView listView;
    List<Product> products = new ArrayList<>();

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    int MY_PRODUCTS_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.keepSynced(true);

        listView = findViewById(R.id.productList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Product p = products.get(position);
                Intent intent = new Intent(ProductsListActivity.this, EditProductActivity.class);
                intent.putExtra("product", p);
                startActivityForResult(intent,MY_PRODUCTS_ACTIVITY_REQUEST_CODE);

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(ProductsListActivity.this).create();
                alertDialog.setTitle("Warning");
                alertDialog.setMessage("Do you want to delete this product?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE ITEM
                        Product product = products.get(position);
                        if (product.getUserEmail().equals(mAuth.getCurrentUser().getEmail())) {
                            databaseReference.child(product.getId() + "").removeValue();
                        } else {
                            Toast.makeText(ProductsListActivity.this, "This isn't your product!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.show();

                return true;
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_PRODUCTS_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Product p =  (Product) data.getSerializableExtra("updatedProduct");
                databaseReference.child(p.getId() + "").setValue(p);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Product product = postSnapshot.getValue(Product.class);
                    if (mAuth.getCurrentUser().getEmail().equals(product.getUserEmail())) {
                        products.add(product);
                    }
                }

                adapter = new ProductListAdapter(products, getLayoutInflater());
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void createList() {
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


//        list.add(new Product("Fond de ten Born This Way", "Fond de ten", 2, 180, "Too Faced", R.drawable.fond_de_ten, new Date(2017 - 1900, 4 - 1, 13)));
//        list.add(new Product("Highlighter", "Iluminator", 1, 200, "Anastasia Beverly Hills", R.drawable.highlighter, new Date(2017 - 1900, 4 - 1, 27)));
//        list.add(new Product("Pudra compacta", "Pudra compacta", 1, 35, "Rimmel London", R.drawable.pudra, new Date(2017 - 1900, 5 - 1, 5)));
//        list.add(new Product("Rimel", "Rimel", 1, 25, "Melkior", R.drawable.rimel, new Date(2017 - 1900, 5 - 1, 29)));
//        list.add(new Product("Ruj baton", "Ruj", 1, 90, "MAC", R.drawable.ruj, new Date(2017 - 1900, 6 - 1, 6)));
//        list.add(new Product("Creion de buze nuanta 06 Satin Mauve", "Creion de buze", 1, 15, "essence", R.drawable.creion_de_buze, new Date(2017 - 1900, 6 - 1, 19)));
//        list.add(new Product("Fond de ten nuanta 02", "Fond de ten", 1, 50, "Bourjois", R.drawable.fond_de_ten_2, new Date(2017 - 1900, 7 - 1, 20)));
//        list.add(new Product("Paleta de farduri Naked 3", "Fard de ochi", 1, 230, "Urban Decay", R.drawable.naked_palette, new Date(2017 - 1900, 7 - 1, 16)));
//        list.add(new Product("Rimel Colossal", "Rimel", 3, 30, "Maybelline New York", R.drawable.rimel_colossal, new Date(2017 - 1900, 8 - 1, 14)));
//        list.add(new Product("Ruj Soft Matte nuanta Copenhagen", "Ruj", 1, 70, "NYX", R.drawable.ruj_copenhagen, new Date(2017 - 1900, 8 - 1, 24)));
//        list.add(new Product("Creion de sprancene nuanta Dark Brown", "Creion de sprancene", 1, 11, "essence", R.drawable.sprancene_darkbrown, new Date(2017 - 1900, 9 - 1, 1)));

    }


}
