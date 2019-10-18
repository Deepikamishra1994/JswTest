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

import com.loyality.jsw.Listerners.CheckDetailsListerner;
import com.loyality.jsw.R;
import com.loyality.jsw.activity.SalesEnquiry;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

public class TMSalesEnquiryAdapter extends RecyclerView.Adapter<TMSalesEnquiryAdapter.MyViewHolder> {
    private AppCompatActivity addProductActivity;
    private List<EnquiryModel> productModels;
QueryStatusListerner queryStatusListerner;
    public interface QueryStatusListerner {
        void onItemClick(EnquiryModel enquiryModel);

    }

    public TMSalesEnquiryAdapter(AppCompatActivity addProductActivity, List<EnquiryModel> productModels, QueryStatusListerner checkDetailsListerner) {
        this.queryStatusListerner = checkDetailsListerner;
        this.addProductActivity = addProductActivity;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public TMSalesEnquiryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(addProductActivity).inflate(R.layout.tm_sales_enquiry_item_list, parent, false);

        return new TMSalesEnquiryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TMSalesEnquiryAdapter.MyViewHolder holder, int position) {

        EnquiryModel addProductModel = productModels.get(position);
        holder.tvCity.setText(addProductModel.getCity());
        holder.tvFabricatorName.setText(addProductModel.getFirstName()+addProductModel.getLastName());
       // holder.tvFabricatorId.setText(addProductModel.getpa());
        holder.tv_query.setText(addProductModel.getQuery());
        holder.tvState.setText(addProductModel.getState());
        holder.tv_status.setText(addProductModel.getStatus());
        holder.tvViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryStatusListerner.onItemClick(addProductModel);
            }
        });
        if (!TextUtils.isEmpty(addProductModel.getStatus())) {

            switch (addProductModel.getStatus()) {

                case "OPEN":
                    holder.tv_status.setTextColor(addProductActivity.getResources().getColor(R.color.colorOrange));
                    holder.tv_status.setText("PENDING");

                    break;

                case "CLOSED":

                    holder.tv_status.setTextColor(addProductActivity.getResources().getColor(R.color.colorGreen));
                    holder.tv_status.setText("ACKNOWLEDGE");


                    break;

            }
        }
    }


    @Override
    public int getItemCount() {
        return productModels.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        AppCompatTextView tvFabricatorId, tvFabricatorName, tvState, tvCity;
        AppCompatTextView tv_query,tvViewDetails,tv_status;

        LinearLayout llSheets;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvState = itemView.findViewById(R.id.tv_state);
            tvFabricatorId = itemView.findViewById(R.id.tv_fabricatorId);
            tvCity = itemView.findViewById(R.id.tv_city);
            tvFabricatorName = itemView.findViewById(R.id.tv_fabricatorName);
            tv_query = itemView.findViewById(R.id.tv_query);
            tvViewDetails = itemView.findViewById(R.id.tv_view);
            tv_status = itemView.findViewById(R.id.tv_status);

            //  llSheets = itemView.findViewById(R.id.llSheets);

        }
    }
}