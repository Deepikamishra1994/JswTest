package com.loyality.jsw.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.Constants.ApplicationConstants;
import com.loyality.jsw.Constants.ApprovalStatus;
import com.loyality.jsw.R;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseVerificationDetailsActivity extends AppCompatActivity implements View.OnClickListener, GetDispatchs, PostDispatchs {

    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.tv_fabricatorId)
    AppCompatEditText tvFabricatorId;
    @BindView(R.id.tv_fabricatorName)
    AppCompatEditText tvFabricatorName;
    @BindView(R.id.tv_fabricatorNumber)
    AppCompatEditText tvFabricatorNumber;
    @BindView(R.id.tv_purchaseDate)
    AppCompatEditText tvPurchaseDate;
    @BindView(R.id.tv_product)
    AppCompatEditText tvProduct;
    @BindView(R.id.tv_amount)
    AppCompatEditText tvAmount;
    @BindView(R.id.tv_quantity)
    AppCompatEditText tvQuantity;
    @BindView(R.id.et_remarks)
    AppCompatEditText etRemarks;
    @BindView(R.id.tvAccept)
    AppCompatTextView tvAccept;
    @BindView(R.id.tv_Reject)
    AppCompatTextView tvReject;
    @BindView(R.id.tv_OnHold)
    AppCompatTextView tvOnHold;
    @BindView(R.id.ll_summary)
    LinearLayout llSummary;
     String status, transactionId="";
    ;
    AddProductModel addProductModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_verification_details_empty);
        ButterKnife.bind(this);
        tvTitle.setText("Purchase Verification Details");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        tvAccept.setOnClickListener(this);
        tvReject.setOnClickListener(this);
        tvOnHold.setOnClickListener(this);
        status = getIntent().getStringExtra("status");
        transactionId = getIntent().getStringExtra("transactionId");
        if (!TextUtils.isEmpty(status)) {

            if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.REJECTED))) {

                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.GONE);
                tvReject.setVisibility(View.GONE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.APPROVED))) {

                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.GONE);
                tvReject.setVisibility(View.GONE);

            }

            else if (status.equalsIgnoreCase("ON-HOLD")) {
                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.VISIBLE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.PENDING))) {


                tvOnHold.setVisibility(View.VISIBLE);
                tvAccept.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.VISIBLE);

            }



        }
        getTransactionDetail(transactionId);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Reject:
                checkRemarksFields("REJECTED");
                break;
            case R.id.tvAccept:
                submit("APPROVED");
                break;
            case R.id.tv_OnHold:
                checkRemarksFields("ON-HOLD");
                break;
        }
    }

    public void checkRemarksFields(String status) {
        if (etRemarks.getText().toString().equalsIgnoreCase(""))
            ApplicationConstants.showToast(this, "Remark cant be Empty");
        else {
            submit(status);
        }



    }
    public void setData() {

    //    addProductModel.setPurchaseDtae(tvPurchaseDate.getText().toString());


             }

    public void getTransactionDetail(String transactionId) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.RETAILER_TRANSACTIONS_DETAIL, transactionId, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    private void submit(String status) {


        if (UtilityMethods.isNetworkAvailable(this)) {

            if(addProductModel !=null){


                addProductModel.setQuantity(tvQuantity.getText().toString());
                addProductModel.setAmount(tvAmount.getText().toString());
                addProductModel.setRemark(etRemarks.getText().toString());
                addProductModel.setTransactionId(transactionId);

                UtilityMethods.showProgressDialog(this, null, "Please Wait...");

                DispatchPostResponse.disptatchRequest(this, ResponseTypes.UPDATE_PURCHASE_VERIFICATION, addProductModel, status, this);

            }




        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }
    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {
        switch (response) {
            case UPDATE_PURCHASE_VERIFICATION:

                UtilityMethods.showToast(PurchaseVerificationDetailsActivity.this, "Status has been updated successfully");
                onBackPressed();
                break;

            case RETAILER_TRANSACTIONS_DETAIL:

                 addProductModel = (AddProductModel) body;

                if (addProductModel != null) {




                   tvAmount .setText(addProductModel.getAmount());
                    tvProduct.setText(addProductModel.getProductName());
                    tvPurchaseDate.setText(addProductModel.getDateOfProduct());
                    tvQuantity.setText(addProductModel.getQuantity());
                    tvFabricatorId.setText(addProductModel.getFabricatorId());
                    tvFabricatorName.setText(addProductModel.getFabricator());
                    tvFabricatorNumber.setText(addProductModel.getFabricatorNumber());

                }


                break;
        }

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }
}
