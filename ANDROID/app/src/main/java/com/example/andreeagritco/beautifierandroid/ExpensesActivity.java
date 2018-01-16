package com.example.andreeagritco.beautifierandroid;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andreeagritco.beautifierandroid.domain.Product;
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

public class ExpensesActivity extends AppCompatActivity {

    ProductListAdapter adapter;
    ListView listView;
    TextView expensesText;

    private int year;
    private int month;
    private int day;
    private boolean isOkayClicked;

    List<Product> products;
    List<Date> dates = new ArrayList<>();

    //total expense for selected day
    double expense = 0;
    //products for which the expense was computed
    List<Product> selectedProducts = new ArrayList<>();


    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_activity);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");


        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
            // when dialog box is closed, below method will be called.
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {
                if (isOkayClicked) {

                    //get all products
//                    databaseReference.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            products.clear();
//
//                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                                Product product = postSnapshot.getValue(Product.class);
//                                if (mAuth.getCurrentUser().getEmail().equals(product.getUserEmail())) {
//                                    products.add(product);
//                                }
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });





                    //retrieve all dates from products
//                    for (Product p : products) {
//                        dates.add(p.getPurchasedDate());
//                    }
//
//                    for (int i = 0; i < dates.size(); i++) {
//
//                        Date date = dates.get(i); // Fri Jun 17 14:54:28 PDT 2016
//                        Calendar cal = Calendar.getInstance();
//                        cal.setTime(date);
//                        int day = cal.get(Calendar.DAY_OF_MONTH);
//                        int month = cal.get(Calendar.MONTH);
//                        month++;
//                        int year = cal.get(Calendar.YEAR);
//
//                        //if date of product is equal with selected date
//                        if (year == selectedYear && month == (selectedMonth + 1) && day == selectedDay) {
//                            expense = expense + (products.get(i).getPrice() * products.get(i).getQuantity());
//                            selectedProducts.add(products.get(i));
//                        }
//                    }
//
//                    listView = findViewById(R.id.productList);
//                    adapter = new ProductListAdapter(selectedProducts, getLayoutInflater());
//                    listView.setAdapter(adapter);
//
//                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position,
//                                                long id) {
//
//                            Intent intent = new Intent(ExpensesActivity.this, PowerUserActivity.class);
//                            intent.putExtra("product", selectedProducts.get(position));
//                            startActivity(intent);
//
//                        }
//                    });
//
//
//                    expensesText = findViewById(R.id.expensesText);
//                    expensesText.setText("Total expenses:" + expense + "");
//                    expensesText.setVisibility(View.VISIBLE);


                }
                isOkayClicked = false;
            }
        };

        //create DatePicker
        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                ExpensesActivity.this, datePickerListener,
                year, month, day);

        //Cancel Button
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        if (which == DialogInterface.BUTTON_NEGATIVE) {
                            dialog.cancel();
                            isOkayClicked = false;

                            Intent returnIntent = new Intent(ExpensesActivity.this, PowerUserActivity.class);
                            startActivity(returnIntent);
                            finish();

                        }
                    }
                });

        //Ok Button
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            isOkayClicked = true;
                            DatePicker datePicker = datePickerDialog
                                    .getDatePicker();
                            datePickerListener.onDateSet(datePicker,
                                    datePicker.getYear(),
                                    datePicker.getMonth(),
                                    datePicker.getDayOfMonth());
                        }
                    }
                });

        datePickerDialog.setCancelable(false);
        datePickerDialog.show();


    }

}


