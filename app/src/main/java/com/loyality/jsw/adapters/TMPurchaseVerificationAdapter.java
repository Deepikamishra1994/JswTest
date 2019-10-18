package com.loyality.jsw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.R;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

public class TMPurchaseVerificationAdapter  extends RecyclerView.Adapter<TMPurchaseVerificationAdapter.MyViewHolder> {
    private AppCompatActivity addProductActivity;
    private List<TranscationModel> productModels;
    StatusListerner statusListerner;static  String remarks="";


    public interface StatusListerner {
        void accept(TranscationModel transcationModel);

    }
    public TMPurchaseVerificationAdapter(AppCompatActivity addProductActivity, List<TranscationModel> productModels, StatusListerner statusListerner) {

        this.addProductActivity = addProductActivity;
        this.productModels = productModels;
        this.statusListerner =statusListerner;
    }

    @NonNull
    @Override
    public TMPurchaseVerificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(addProductActivity).inflate(R.layout.purchase_verifyitem_list, parent,false);

        return new TMPurchaseVerificationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TMPurchaseVerificationAdapter.MyViewHolder holder, int position) {

        TranscationModel fabricatorPurchase = productModels.get(position);
        holder.tvProduct.setText(fabricatorPurchase.getProduct());
        holder.tvFabricatorName.setText(fabricatorPurchase.getFabricatorName());
        holder.tvFabricatorId.setText(fabricatorPurchase.getFabricatorID());
        holder.tvPurchaseDate.setText(fabricatorPurchase.getPurchaseDtae());
        holder.tvStatus.setText(fabricatorPurchase.getStatus());
//        if (TextUtils.isEmpty(addProductModel.getSheets())) {
//
//            holder.llSheets.setVisibility(View.GONE);
//        } else {
//
//            holder.llSheets.setVisibility(View.VISIBLE);
//           // holder.tvSheets.setText(addProductModel.getSheets());
//
//        }


        holder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               statusListerner.accept(fabricatorPurchase);

            }
        });

    }


    @Override
    public int getItemCount() {
        return productModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        AppCompatTextView tvFabricatorId,tvFabricatorName,tvPurchaseDate,tvProduct,tvStatus;  AppCompatEditText tvRemarks;
        AppCompatTextView tvAccept,tvReject,tvOnHold,tvViewDetails;


        LinearLayout llSheets;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvProduct = itemView.findViewById(R.id.tv_product);
            tvFabricatorId = itemView.findViewById(R.id.tv_fabricatorId);
            tvPurchaseDate = itemView.findViewById(R.id.tv_purchaseDate);
            tvFabricatorName = itemView.findViewById(R.id.tv_fabricatorName);
            tvRemarks = itemView.findViewById(R.id.et_remarks);
            tvAccept = itemView.findViewById(R.id.tvAccept);
            tvReject = itemView.findViewById(R.id.tv_Reject);
            tvOnHold = itemView.findViewById(R.id.tv_OnHold);
            tvViewDetails = itemView.findViewById(R.id.tv_view);
            tvStatus = itemView.findViewById(R.id.tv_status);


        }
    }
}
