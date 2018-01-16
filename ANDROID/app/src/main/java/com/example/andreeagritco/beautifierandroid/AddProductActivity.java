package com.example.andreeagritco.beautifierandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddProductActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    Spinner productTypeSpinner;
    Spinner brandSpinner;
    EditText descriptionText;
    EditText quantityText;
    EditText priceText;

    Button saveButton;
    Button cancelButton;

    List<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        instantiateComponents();

        populateSpinners();

        allProducts = new ArrayList<>();


        //Cancel Button Listener
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent;
                if (mAuth.getCurrentUser().getEmail().equals("gritco.andreea@gmail.com")) {
                    returnIntent = new Intent(AddProductActivity.this, PowerUserActivity.class);
                } else {
                    returnIntent = new Intent(AddProductActivity.this, NormalUserActivity.class);
                }

                startActivity(returnIntent);
                finish();
            }
        });

        saveButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                if (descriptionText.getText().toString().trim().equals("") || quantityText.getText().toString().trim().equals("") || priceText.getText().toString().trim().equals("")) {
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
                } else {

                    Date currentDate = new Date(Calendar.getInstance().get(Calendar.YEAR) - 1900, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                    final Product p = new Product(descriptionText.getText().toString(), productTypeSpinner.getSelectedItem().toString(), Integer.parseInt(quantityText.getText().toString()), Double.parseDouble(priceText.getText().toString()), brandSpinner.getSelectedItem().toString(), 0, currentDate, mAuth.getCurrentUser().getEmail());

//
//                    for (Product pr : allProducts) {
//                        if (pr.getDescription().equals(p.getDescription())) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
//                            builder.setMessage("There already exists a product with this description in the database!")
//                                    .setTitle("Warning");
//
//                            //Set cancel button
//                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.dismiss();
//
//                                    Intent returnIntent = new Intent(AddProductActivity.this, MainActivity.class);
//                                    startActivity(returnIntent);
//                                    finish();
//
//                                }
//                            });
//
//                            //Create and show dialog
//                            AlertDialog dialog = builder.create();
//                            dialog.show();
//                            break;
//                        } else {
                            String id = databaseReference.push().getKey();
                            p.setId(id);
                            databaseReference.child(p.getId()).setValue(p);
                            Toast.makeText(AddProductActivity.this, "Product added successfully!", Toast.LENGTH_LONG).show();
                        //    break;
//                        }
//                    }


                    Intent returnIntent;
                    if (mAuth.getCurrentUser().getEmail().equals("gritco.andreea@gmail.com")) {
                        returnIntent = new Intent(AddProductActivity.this, PowerUserActivity.class);
                    } else {
                        returnIntent = new Intent(AddProductActivity.this, NormalUserActivity.class);
                    }

                    startActivity(returnIntent);
                    finish();


                }
            }
        });


    }

    private void populateSpinners() {

        List<String> types = ProductType.returnTypes();
        List<String> brands = BrandType.returnBrands();

        ArrayAdapter<String> productTypeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, types);
        productTypeSpinner.setAdapter(productTypeArrayAdapter);

        ArrayAdapter<String> brandTypeArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, brands);
        brandSpinner.setAdapter(brandTypeArrayAdapter);

    }


    private void instantiateComponents() {
        descriptionText = findViewById(R.id.descriptionText);
        quantityText = findViewById(R.id.quantityText);
        priceText = findViewById(R.id.priceText);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        productTypeSpinner = findViewById(R.id.productTypeSpinner);
        brandSpinner = findViewById(R.id.brandSpinner);
    }
}
