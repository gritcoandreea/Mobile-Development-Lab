package com.example.andreeagritco.beautifierandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.andreeagritco.beautifierandroid.domain.Product;
import com.example.andreeagritco.beautifierandroid.utils.AppDatabase;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    private static final int EDIT_ACTIVITY_RESULT_CODE = 0;


    Button addProductButton;
    Button listButton;
    Button expensesButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findComponents();

        listButton.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Intent returnIntent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(returnIntent);
            }
        });


        addProductButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent(MainActivity.this, AddProductActivity.class);
                startActivity(returnIntent);
            }
        });

        expensesButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent returnIntent = new Intent(MainActivity.this, ExpensesActivity.class);
                startActivity(returnIntent);
            }
        });


    }

    private void findComponents() {
        listButton = findViewById(R.id.listButton);
        addProductButton = findViewById(R.id.addButton);
        expensesButton = findViewById(R.id.expensesButton);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    
}
