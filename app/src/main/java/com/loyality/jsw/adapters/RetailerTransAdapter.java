package com.loyality.jsw.adapters;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.R;
import com.loyality.jsw.RetailerTranscationActivity;
import com.loyality.jsw.RetailerTranscationDetailActivity;
import com.loyality.jsw.TranscationHistoryActivity;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

public class RetailerTransAdapter extends RecyclerView.Adapter<RetailerTransAdapter.MyViewHolder> {
   private RetailerTranscationActivity transcationHistoryActivity;
  private List<TranscationModel> transcationModels;

    public RetailerTransAdapter(RetailerTranscationActivity transcationHistoryActivity, List<TranscationModel> eventModelList) {

        this.transcationHistoryActivity = transcationHistoryActivity;
        transcationModels =eventModelList;
    }

    @NonNull
    @Override
    public RetailerTransAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(transcationHistoryActivity).inflate(R.layout.custom_retailer_transcation,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetailerTransAdapter.MyViewHolder holder, int position) {

       TranscationModel transcationModel = transcationModels.get(position);


        Log.e("TestingTransc",""+transcationModel.getStatus());
       holder.tvTranscation.setText(transcationModel.getTransactionId());
       holder.tvSize.setText(transcationModel.getSize());


           holder.tvStatus.setText(transcationModel.getStatus());



       holder.tvFabricator.setText(transcationModel.getFabricator());
       holder.tvAmount.setText(transcationModel.getAmount());
       holder.tvProduct.setText(transcationModel.getProductName());
       holder.tvQuantity.setText(transcationModel.getQuantity());
       holder.tvUnit.setText(transcationModel.getUnit());
       holder.tvDate.setText(transcationModel.getDate());

       if(TextUtils.isEmpty(transcationModel.getSheets())){

           holder.llSheets.setVisibility(View.GONE);
       }
       else{

           holder.llSheets.setVisibility(View.VISIBLE);
           holder.tvSheets.setText(transcationModel.getSheets());

       }
       holder.llRoot.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               transcationHistoryActivity.startActivity(new Intent(transcationHistoryActivity, RetailerTranscationDetailActivity.class).putExtra("transactionId",transcationModel.getTransactionId()).putExtra("status",transcationModel.getStatus()));

           }
       });

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
        AppCompatTextView tvFabricator;
        AppCompatTextView tvStatus;
        AppCompatTextView  tvDate;
        AppCompatTextView tvSheets;
        LinearLayout llSheets;
        LinearLayout llRoot;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTranscation = itemView.findViewById(R.id.tvTranscationId);
            tvProduct = itemView.findViewById(R.id.tvProduct);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvQuantity = itemView.findViewById(R.id.tvQunatity);
            tvUnit = itemView.findViewById(R.id.tvUnit);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvFabricator = itemView.findViewById(R.id.tvFabricator);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvSheets = itemView.findViewById(R.id.tvSheets);
            llSheets = itemView.findViewById(R.id.llSheets);
            llRoot = itemView.findViewById(R.id.llRoot);


        }
    }

}
