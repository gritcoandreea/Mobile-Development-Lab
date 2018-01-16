package com.example.andreeagritco.beautifierandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EditProductActivity extends AppCompatActivity {


    Spinner productTypeSpinner;
    Spinner brandSpinner;
    EditText descriptionText;
    EditText quantityText;
    EditText priceText;

    Button saveButton;
    Button cancelButton;

    Product p;

    GraphView graph;

    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_layout);


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");
        databaseReference.keepSynced(true);


        Intent i = getIntent();
        p = (Product) i.getSerializableExtra("product");


        findViewComponents();

        populateSpinners();

        populateTextFields();

        //Cancel Button Listener
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent(EditProductActivity.this, ProductsListActivity.class);
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });

        //Save Button Listener
        saveButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                //  Product p2 = new Product(p.getId(), p.getDescription(), p.getProductType(), p.getQuantity(), p.getPrice(), p.getBrand(), 0);

                if (!descriptionText.getText().toString().equals("")) {
                    p.setDescription(descriptionText.getText().toString());
                }
                p.setProductType(productTypeSpinner.getSelectedItem().toString());
                if (!quantityText.getText().toString().equals("")) {
                    p.setQuantity(Integer.parseInt(quantityText.getText().toString()));
                }
                if (!priceText.getText().toString().equals("")) {
                    p.setPrice(Double.parseDouble(priceText.getText().toString()));
                }
                p.setBrand(brandSpinner.getSelectedItem().toString());

                //  sendEmail(p2);

                Intent returnIntent = new Intent(EditProductActivity.this, ProductsListActivity.class);
                returnIntent.putExtra("updatedProduct", p);
                // startActivity(returnIntent);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });

        Calendar cal = Calendar.getInstance();
        cal.setTime(p.getPurchasedDate());


        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH); // 1 (months begin with 0)
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month++;

        if (mAuth.getCurrentUser().getEmail().equals("gritco.andreea@gmail.com")) {

//                     try {
//                List<Product> productList = new AsyncTask<Void, Void, List<Product>>() {
//                    @Override
//                    protected List<Product> doInBackground(Void... params) {
//                        return db.productDao().getAll();
//
//                    }
//                }.execute().get();
//
//                List<Product> productsFromThisMonth = new ArrayList<>();
//
//                Calendar cal2 = Calendar.getInstance();
//
//                for (Product p : productList) {
//                    Date date = p.getPurchasedDate();
//
//
//                    cal2.setTime(date);
//                    int day2 = cal2.get(Calendar.DAY_OF_MONTH);
//                    int month2 = cal2.get(Calendar.MONTH);
//                    month2++;
//
//                    if (month == month2) {
//                        productsFromThisMonth.add(p);
//                    }
//
//                }
//
//                // Create a calendar object and set year and month
//                Calendar mycal = new GregorianCalendar(year, month--, 1);
//
//                // Get the number of days in that month
//                // int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//                int daysInMonth = 31;
//
//                double sum = 0;
//
//
//                DataPoint[] points = new DataPoint[daysInMonth];
//
//                for (int j = 1; j <= daysInMonth; j++) {
//
//                    for (Product p : productsFromThisMonth) {
//                        Date date = p.getPurchasedDate();
//
//                        Calendar cal3 = Calendar.getInstance();
//                        cal3.setTime(date);
//                        int day2 = cal3.get(Calendar.DAY_OF_MONTH);
//
//                        if (day2 == j) {
//                            sum = sum + p.getQuantity() * p.getPrice();
//                        }
//
//                    }
//
//                    points[j - 1] = new DataPoint(j, (int) sum);
//                    sum = 0;
//                }
//
//
//                LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
//                graph.addSeries(series);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//

        } else {
            graph.setVisibility(View.INVISIBLE);
        }

// catch (ExecutionException e) {
//            e.printStackTrace();
//        }


//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void findViewComponents() {
        descriptionText = findViewById(R.id.descriptionText);
        quantityText = findViewById(R.id.quantityText);
        priceText = findViewById(R.id.priceText);

        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        productTypeSpinner = findViewById(R.id.productTypeSpinner);
        brandSpinner = findViewById(R.id.brandSpinner);

        graph = findViewById(R.id.graph);

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

        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).equals(p.getProductType())) {
                productTypeSpinner.setSelection(i);
            }
        }
        for (int i = 0; i < brands.size(); i++) {
            if (brands.get(i).equals(p.getBrand())) {
                brandSpinner.setSelection(i);
            }
        }
    }

    private void populateTextFields() {
        descriptionText.setText(p.getDescription());
        quantityText.setText(p.getQuantity() + "");
        priceText.setText(p.getPrice() + "");
    }

    private void sendEmail(Product p2) {
        String s = "";
        s += p2.toString();
        s += "\n\n was updated to:  \n\n";
        s += p.toString();

        String[] TO = {"gritco.andreea@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Product updated!");
        emailIntent.putExtra(Intent.EXTRA_TEXT, s);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EditProductActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


}
