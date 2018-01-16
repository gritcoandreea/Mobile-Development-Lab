package com.example.andreeagritco.beautifierandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NormalUserActivity extends AppCompatActivity {

    Button addProductButton;
    Button listButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user);

        listButton = findViewById(R.id.listButton);
        addProductButton = findViewById(R.id.addButton);

        listButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent returnIntent = new Intent(NormalUserActivity.this, ProductsListActivity.class);
                startActivity(returnIntent);
            }
        });


        addProductButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent(NormalUserActivity.this, AddProductActivity.class);
                startActivity(returnIntent);
            }
        });
    }
}
