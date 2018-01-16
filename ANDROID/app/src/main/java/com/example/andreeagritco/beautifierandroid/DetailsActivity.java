//package com.example.andreeagritco.beautifierandroid;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.andreeagritco.beautifierandroid.domain.Product;
//
//import java.util.Calendar;
//
//public class DetailsActivity extends AppCompatActivity {
//
//    private static final int EDIT_ACTIVITY_RESULT_CODE = 5;
//    private static final int DETAILS_ACTIVITY_RESULT_CODE = 1;
//
//    private Product p;
//    private AppDatabase db;
//
//    EditText descriptionText;
//    EditText productTypeText;
//    EditText quantityText;
//    EditText priceText;
//    EditText brandText;
//    EditText dateText;
//
//    Button editButton;
//    Button deleteButton;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.details);
//
//        //Instantiate the database
//        db = AppDatabase.getAppDatabase(getApplicationContext());
//        //get product from Main Activity
//        Intent i = getIntent();
//        p = (Product) i.getSerializableExtra("product");
//
//        //Instantiate textfields
//        findTextFields();
//        //Set data in fields
//        setDataInTextFields();
//
//        editButton.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(DetailsActivity.this, EditProductActivity.class);
//                intent.putExtra("product", p);
//                startActivityForResult(intent, EDIT_ACTIVITY_RESULT_CODE);
//                finish();
//            }
//        });
//
//        deleteButton.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
//                builder.setMessage("Are you sure you want to delete this item?")
//                        .setTitle("Warning");
//
//
//                //Set detelete button
//                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button
//                        new AsyncTask<Void, Void, Integer>() {
//                            @Override
//                            protected Integer doInBackground(Void... params) {
//                                db.productDao().delete(p);
//                                return 1;
//                            }
//                        }.execute();
//
//                        //Return to main activity after we delete the product
//                        Intent returnIntent = new Intent(DetailsActivity.this, MainActivity.class);
//                        startActivity(returnIntent);
//                        finish();
//
//
//                    }
//                });
//
//                //Set cancel button
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.dismiss();
//
//                    }
//                });
//
//                //Create and show dialog
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//
//
//
//            }
//        });
//    }
//
//
//    private void findTextFields() {
//        descriptionText = findViewById(R.id.descriptionText);
//        productTypeText = findViewById(R.id.productTypeText);
//        quantityText = findViewById(R.id.quantity2Text);
//        priceText = findViewById(R.id.priceText);
//        brandText = findViewById(R.id.brandText);
//        dateText = findViewById(R.id.dateText);
//
//        editButton = findViewById(R.id.editButton);
//        deleteButton = findViewById(R.id.deleteButton);
//
//    }
//
//    private void setDataInTextFields() {
//        descriptionText.setText(p.getDescription());
//        productTypeText.setText(p.getProductType());
//        quantityText.setText(p.getQuantity() + "");
//        priceText.setText(p.getPrice() + "");
//        brandText.setText(p.getBrand());
//
//        setDate();
//    }
//
//    private void setDate() {
//        String date = "";
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(p.getPurchasedDate());
//        date += calendar.get(Calendar.DAY_OF_MONTH) + ".";
//        date += calendar.get(Calendar.MONTH) + ".";
//        date += (calendar.get(Calendar.YEAR) - 1900) + "";
//
//        dateText.setText(date);
//    }
//}
