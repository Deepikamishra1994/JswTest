package com.loyality.jsw.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loyality.jsw.R;
import com.loyality.jsw.serverrequesthandler.models.ProductModel;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<ProductModel> sortList;
    private Context context;

    public ProductAdapter(AppCompatActivity activity, List<ProductModel> sortList) {
        context = activity;
        this.sortList = sortList;
    }

    @Override
    public int getCount() {
        return sortList.size();
    }

    @Override
    public Object getItem(int i) {
        return sortList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom_spinner, null);
        TextView textView = view.findViewById(R.id.tvName);
        textView.setText(sortList.get(i).getProductName());
        return view;
    }
}

