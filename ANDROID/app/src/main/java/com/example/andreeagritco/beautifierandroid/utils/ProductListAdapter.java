package com.example.andreeagritco.beautifierandroid.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andreeagritco.beautifierandroid.R;
import com.example.andreeagritco.beautifierandroid.domain.Product;

import java.util.List;

/**
 * Created by Andreea Gritco on 08-Nov-17.
 */

public class ProductListAdapter extends BaseAdapter {
    List<Product> productList;
    LayoutInflater inflater;

    public ProductListAdapter(List<Product> productList, LayoutInflater inflater) {
        this.productList = productList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_product_layout, null);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.descriptionText);
        TextView brandView = (TextView) convertView.findViewById(R.id.textBrand);

        imageView.setImageResource(productList.get(position).getImagePath());
        descriptionView.setText(productList.get(position).getDescription());
        brandView.setText(productList.get(position).getBrand());

        return convertView;
    }
}
