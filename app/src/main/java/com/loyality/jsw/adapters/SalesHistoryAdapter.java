package com.loyality.jsw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.R;
import com.loyality.jsw.TranscationHistoryActivity;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

public class SalesHistoryAdapter extends RecyclerView.Adapter<SalesHistoryAdapter.MyViewHolder> {
   private AppCompatActivity transcationHistoryActivity;
  private List<TranscationModel> transcationModels;

    public SalesHistoryAdapter(AppCompatActivity transcationHistoryActivity, List<TranscationModel> eventModelList) {

        this.transcationHistoryActivity = transcationHistoryActivity;
        transcationModels =eventModelList;
    }

    @NonNull
    @Override
    public SalesHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(transcationHistoryActivity).inflate(R.layout.custom_transcation,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesHistoryAdapter.MyViewHolder holder, int position) {

       TranscationModel transcationModel = transcationModels.get(position);
       holder.tvTranscation.setText(transcationModel.getTransactionId());
       holder.tvSize.setText(transcationModel.getSize());
       holder.tvStatus.setText(transcationModel.getStatus());
       holder.tvRetailer.setText(transcationModel.getRetailer());
       holder.tvAmount.setText(transcationModel.getAmount());
       holder.tvProduct.setText(transcationModel.getProduct());
       holder.tvQuantity.setText(transcationModel.getQuantity());
       holder.tvUnit.setText(transcationModel.getUnit());
       holder.tvDate.setText(transcationModel.getDate());


    }


    @Override
    public int getItemCount() {
        return transcationModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{



        AppCompatTextView tvTranscation;

        AppCompatTextView tvProduct;

        AppCompatTextView tvQuantity;

        AppCompatTextView tvUnit;
        AppCompatTextView tvSize;

        AppCompatTextView tvAmount;
        AppCompatTextView tvRetailer;
        AppCompatTextView tvStatus;
        AppCompatTextView  tvDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTranscation = itemView.findViewById(R.id.tvTranscationId);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvQuantity = itemView.findViewById(R.id.tvQunatity);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvRetailer = itemView.findViewById(R.id.tvRetailer);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);


        }
    }

}
