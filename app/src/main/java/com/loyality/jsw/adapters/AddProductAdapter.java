package com.loyality.jsw.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.AddProductActivity;
import com.loyality.jsw.R;
import com.loyality.jsw.TranscationHistoryActivity;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

public class AddProductAdapter extends RecyclerView.Adapter<AddProductAdapter.MyViewHolder> {
   private AppCompatActivity addProductActivity;
  private List<AddProductModel> productModels;

    public AddProductAdapter(AppCompatActivity addProductActivity, List<AddProductModel> productModels) {

        this.addProductActivity = addProductActivity;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public AddProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(addProductActivity).inflate(R.layout.custom_add_product,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddProductAdapter.MyViewHolder holder, int position) {

       AddProductModel addProductModel = productModels.get(position);
       holder.tvSize.setText(addProductModel.getSize());
       holder.tvRetailer.setText(addProductModel.getRetailer());
       holder.tvAmount.setText(addProductModel.getAmount());
       holder.tvProduct.setText(addProductModel.getProductName());
       holder.tvQuantity.setText(addProductModel.getQuantity());
       holder.tvUnit.setText(addProductModel.getUnit());
       holder.tvDate.setText(addProductModel.getDateOfProduct());
        if(TextUtils.isEmpty(addProductModel.getSheets())){

            holder.llSheets.setVisibility(View.GONE);
        }
        else{

            holder.llSheets.setVisibility(View.VISIBLE);
            holder.tvSheets.setText(addProductModel.getSheets());

        }

    }


    @Override
    public int getItemCount() {
        return productModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{




        AppCompatTextView tvProduct;

        AppCompatTextView tvQuantity;

        AppCompatTextView tvUnit;
        AppCompatTextView tvSize;

        AppCompatTextView tvAmount;
        AppCompatTextView tvRetailer;
        AppCompatTextView tvDate;
        AppCompatTextView tvSheets;
        LinearLayout llSheets;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvQuantity = itemView.findViewById(R.id.tvQunatity);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvRetailer = itemView.findViewById(R.id.tvRetailer);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSheets = itemView.findViewById(R.id.tvSheets);
            llSheets = itemView.findViewById(R.id.llSheets);


        }
    }

}
