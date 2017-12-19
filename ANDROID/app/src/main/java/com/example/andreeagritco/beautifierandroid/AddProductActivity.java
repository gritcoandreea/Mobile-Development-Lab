package com.example.andreeagritco.beautifierandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.andreeagritco.beautifierandroid.domain.BrandType;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductType;
import com.example.andreeagritco.beautifierandroid.utils.AppDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddProductActivity extends AppCompatActivity {

    private AppDatabase db;

    Spinner productTypeSpinner;
    Spinner brandSpinner;
    EditText descriptionText;
    EditText quantityText;
    EditText priceText;

    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Instantiate the database
        db = AppDatabase.getAppDatabase(getApplicationContext());

        instantiateComponents();

        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                populateSpinners();
                return 1;
            }

        }.execute();


        //Cancel Button Listener
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(returnIntent);
                finish();
            }
        });

        saveButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v){

                if(descriptionText.getText().toString().trim().equals("") || quantityText.getText().toString().trim().equals("") || priceText.getText().toString().trim().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
                    builder.setMessage("Some of the details are not completed!")
                            .setTitle("Warning");

                    //Set cancel button
                    builder.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                        }
                    });

                    //Create and show dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{

                    Date currentDate = new Date(Calendar.getInstance().get(Calendar.YEAR) - 1900, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    final Product p = new Product(descriptionText.getText().toString(),productTypeSpinner.getSelectedItem().toString(),Integer.parseInt(quantityText.getText().toString()),Double.parseDouble(priceText.getText().toString()),brandSpinner.getSelectedItem().toString(),0,currentDate);

                    try {
                        int result =  new AsyncTask<Void, Void, Integer>() {
                             @Override
                             protected Integer doInBackground(Void... params) {

                                 List<Product> products = db.productDao().getAll();
                                 for(Product p2 : products){
                                     if(p2.getDescription().equals(p.getDescription())){

                                        return -1;
                                     }
                                 }

                                 db.productDao().insertProduct(p);
                                 return 1;
                             }
                         }.execute().get();

                        if(result == -1){
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
                            builder.setMessage("There already exists a product with this description in the database!")
                                    .setTitle("Warning");

                            //Set cancel button
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();

                                    Intent returnIntent = new Intent(AddProductActivity.this,MainActivity.class);
                                    startActivity(returnIntent);
                                    finish();

                                }
                            });

                            //Create and show dialog
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }else{
                            Intent returnIntent = new Intent(AddProductActivity.this,MainActivity.class);
                            startActivity(returnIntent);
                            finish();
                        }



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            }
        });




    }

    private void populateSpinners() {

        List<ProductType> types = db.productTypeDao().getAllProductTypes();
        List<BrandType> brands = db.brandTypeDao().getAllBrandTypes();

        ArrayAdapter<ProductType> productTypeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        productTypeSpinner.setAdapter(productTypeArrayAdapter);

        ArrayAdapter<BrandType> brandTypeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, brands);
        brandSpinner.setAdapter(brandTypeArrayAdapter);

    }


    private void instantiateComponents(){
        descriptionText = findViewById(R.id.descriptionText);
        quantityText = findViewById(R.id.quantityText);
        priceText = findViewById(R.id.priceText);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        productTypeSpinner = findViewById(R.id.productTypeSpinner);
        brandSpinner = findViewById(R.id.brandSpinner);
    }
}
