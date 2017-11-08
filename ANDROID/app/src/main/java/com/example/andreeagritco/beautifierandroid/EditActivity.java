package com.example.andreeagritco.beautifierandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andreeagritco.beautifierandroid.domain.BrandTypes;
import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.domain.ProductTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EditActivity extends AppCompatActivity {


    Spinner productTypeSpinner;
    Spinner brandSpinner;
    EditText descriptionText;
    EditText quantityText;
    EditText priceText;
    Product p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_layout);

        Intent i = getIntent();
        p = (Product) i.getSerializableExtra("product");


        descriptionText = (EditText) findViewById(R.id.descriptionText);
        quantityText = (EditText) findViewById(R.id.quantityText);
        priceText = (EditText) findViewById(R.id.priceText);


        createLists();
        descriptionText.setText(p.getDescription());
        quantityText.setText(p.getQuantity() + "");
        priceText.setText(p.getPrice() + "");




        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });

        saveButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Product p2 = new Product(p.getId(),p.getDescription(),p.getProductType(),p.getQuantity(),p.getPrice(),p.getBrand(),0);

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

                sendEmail(p2);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", p);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });


    }

    private void sendEmail(Product p2){
        String s="";
        s+=p2.toString();
        s+="\n\n was updated to:  \n\n";
        s+=p.toString();



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
            Toast.makeText(EditActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }



    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private void createLists() {
        productTypeSpinner = (Spinner) findViewById(R.id.productTypeSpinner);
        brandSpinner = (Spinner) findViewById(R.id.brandSpinner);

        List<String> types = ProductTypes.returnTypes();
        List<String> brands = BrandTypes.returnBrands();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, types);
        productTypeSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, brands);
        brandSpinner.setAdapter(adapter2);

      for(int i=0;i<types.size();i++){
          if(types.get(i).equals(p.getProductType())){
              productTypeSpinner.setSelection(i);
          }
      }


        for(int i=0;i<brands.size();i++){
            if(brands.get(i).equals(p.getBrand())){
                brandSpinner.setSelection(i);
            }
        }

    }


}
