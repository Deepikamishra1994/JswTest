package com.loyality.jsw.adapters;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.R;
import com.loyality.jsw.RetailerTranscationActivity;
import com.loyality.jsw.RetailerTranscationDetailActivity;
import com.loyality.jsw.TransactionHistoryDetail;
import com.loyality.jsw.TranscationHistoryActivity;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.io.Serializable;
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

       View view= LayoutInflater.from(transcationHistoryActivity).inflate(R.layout.custom_transcation,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetailerTransAdapter.MyViewHolder holder, int position) {






        TranscationModel transcationModel = transcationModels.get(position);
        holder.tvFabricator.setText(transcationModel.getFabricator());
        holder.tvStatus.setText(transcationModel.getStatus());


        if(!TextUtils.isEmpty(transcationModel.getStatus())) {

            switch (transcationModel.getStatus()) {

                case "PENDING":

                    holder.tvStatus.setTextColor(transcationHistoryActivity.getResources().getColor(R.color.colorOrange));

                    break;

                case "APPROVED":

                    holder.tvStatus.setTextColor(transcationHistoryActivity.getResources().getColor(R.color.colorGreen));

                    break;
                case "REJECTED":

                    holder.tvStatus.setTextColor(transcationHistoryActivity.getResources().getColor(R.color.colorRed));

                    break;
            }

        }

        holder.iv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transcationHistoryActivity.startActivity(new Intent(transcationHistoryActivity, RetailerTranscationDetailActivity.class).putExtra("transactionId",transcationModel.getTransactionId()).putExtra("status",transcationModel.getStatus()));

            }
        });

    }


    @Override
    public int getItemCount() {
        return transcationModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{



        AppCompatTextView tvFabricator;
        AppCompatTextView tvStatus;
        //        AppCompatTextView  tvDate;
        ImageView iv_view;
//

        LinearLayout llSheets;
        LinearLayout llRoot;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            // tvTranscation = itemView.findViewById(R.id.tvTranscationId);
//            tvProduct = itemView.findViewById(R.id.tvProduct);
//            tvSize = itemView.findViewById(R.id.tvSize);
//            tvQuantity = itemView.findViewById(R.id.tvQunatity);
//            tvUnit = itemView.findViewById(R.id.tvUnit);
//            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvFabricator = itemView.findViewById(R.id.tvRetailer);
            tvStatus = itemView.findViewById(R.id.tvStatus);
//            tvDate = itemView.findViewById(R.id.tvDate);
            iv_view = itemView.findViewById(R.id.iv_view);
//            llSheets = itemView.findViewById(R.id.llSheets);
//            llRoot = itemView.findViewById(R.id.llRoot);



        }
    }

}
