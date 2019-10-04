package com.loyality.jsw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.loyality.jsw.Constants.ApprovalStatus;
import com.loyality.jsw.Constants.DatePickerFragment;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetailerTranscationDetailActivity extends AppCompatActivity implements GetDispatchs, PostDispatchs {

    String state, city, district;

    String productName, size, retailer, price, unit, sheets;
    List<AddProductModel> addProductModelList = new ArrayList<>();


    double totalValue;


    String status, transactionId;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.etDatePurchase)
    AppCompatEditText etDatePurchase;
    @BindView(R.id.etProductName)
    AppCompatTextView etProductName;
    @BindView(R.id.etSize)
    AppCompatTextView etSize;
    @BindView(R.id.etUnit)
    AppCompatTextView etUnit;
    @BindView(R.id.etQunatity)
    AppCompatEditText etQunatity;
    @BindView(R.id.tvSheetTitle)
    AppCompatTextView tvSheetTitle;
    @BindView(R.id.etSheets)
    AppCompatEditText etSheets;
    @BindView(R.id.etAmount)
    AppCompatEditText etAmount;
    @BindView(R.id.etRemarks)
    AppCompatEditText etRemarks;
    @BindView(R.id.btnReject)
    AppCompatButton btnReject;
    @BindView(R.id.btnOnHold)
    AppCompatButton btnOnHold;
    @BindView(R.id.btnApprove)
    AppCompatButton btnApprove;
    @BindView(R.id.tvValue)
    AppCompatTextView tvValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation_detail);
        ButterKnife.bind(this);
        tvTitle.setText("Transaction Detail");
        etDatePurchase.setEnabled(false);

        status = getIntent().getStringExtra("status");
        transactionId = getIntent().getStringExtra("transactionId");
        if (!TextUtils.isEmpty(status)) {

            if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.REJECTED))) {

                btnOnHold.setVisibility(View.GONE);
                btnApprove.setVisibility(View.GONE);
                btnReject.setVisibility(View.GONE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.APPROVED))) {

                btnOnHold.setVisibility(View.GONE);
                btnApprove.setVisibility(View.GONE);
                btnReject.setVisibility(View.GONE);

            }

            else if (status.equalsIgnoreCase("ON HOLD")) {
                btnOnHold.setVisibility(View.GONE);
                btnApprove.setVisibility(View.VISIBLE);
                btnReject.setVisibility(View.VISIBLE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.PENDING))) {


                btnOnHold.setVisibility(View.VISIBLE);
                btnApprove.setVisibility(View.VISIBLE);
                btnReject.setVisibility(View.VISIBLE);

            }



        }


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });


        etDatePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (addProudctValidate()) {

                    AddProductModel addProductModel = new AddProductModel();
                    addProductModel.setAmount(String.valueOf(etAmount.getText()));
                    addProductModel.setDateOfProduct(String.valueOf(etDatePurchase.getText()));
                    addProductModel.setProductName(String.valueOf(etProductName.getText()));
                    addProductModel.setQuantity(String.valueOf(etQunatity.getText()));
                    addProductModel.setSize(String.valueOf(etSize.getText()));
                    addProductModel.setUnit(String.valueOf(etUnit.getText()));
                    addProductModel.setSheets(String.valueOf(etSheets.getText()));
                    addProductModel.setTransactionId(transactionId);
                    addProductModel.setRemarks(String.valueOf(etRemarks.getText()));
                    addProductStatus(addProductModel, String.valueOf(ApprovalStatus.APPROVED));

                }

            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addProudctValidate()) {

                    if (!TextUtils.isEmpty(etRemarks.getText())) {

                        AddProductModel addProductModel = new AddProductModel();
                        addProductModel.setAmount(String.valueOf(etAmount.getText()));
                        addProductModel.setDateOfProduct(String.valueOf(etDatePurchase.getText()));
                        addProductModel.setProductName(String.valueOf(etProductName.getText()));
                        addProductModel.setQuantity(String.valueOf(etQunatity.getText()));
                        addProductModel.setSize(String.valueOf(etSize.getText()));
                        addProductModel.setUnit(String.valueOf(etUnit.getText()));
                        addProductModel.setSheets(String.valueOf(etSheets.getText()));
                        addProductModel.setTransactionId(transactionId);
                        addProductModel.setRemarks(String.valueOf(etRemarks.getText()));
                        addProductStatus(addProductModel, String.valueOf(ApprovalStatus.REJECTED));

                    } else {

                        etRemarks.setError("Enter Remarks");
                    }

                }


            }
        });

        btnOnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addProudctValidate()) {

                    if (!TextUtils.isEmpty(etRemarks.getText())) {

                        AddProductModel addProductModel = new AddProductModel();
                        addProductModel.setAmount(String.valueOf(etAmount.getText()));
                        addProductModel.setDateOfProduct(String.valueOf(etDatePurchase.getText()));
                        addProductModel.setProductName(String.valueOf(etProductName.getText()));
                        addProductModel.setQuantity(String.valueOf(etQunatity.getText()));
                        addProductModel.setSize(String.valueOf(etSize.getText()));
                        addProductModel.setUnit(String.valueOf(etUnit.getText()));
                        addProductModel.setSheets(String.valueOf(etSheets.getText()));
                        addProductModel.setTransactionId(transactionId);
                        addProductModel.setRemarks(String.valueOf(etRemarks.getText()));
                        addProductStatus(addProductModel, String.valueOf(ApprovalStatus.ON_HOLD));

                    } else {

                        etRemarks.setError("Enter Remarks");
                    }

                }


            }
        });


        getTransactionDetail(transactionId);

        //  getAllRetailers();

    }


    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public boolean addProudctValidate() {

        String dateOfProduct = String.valueOf(etDatePurchase.getText());
        String qty = String.valueOf(etQunatity.getText());
        String amount = String.valueOf(etAmount.getText());
        String sheets = String.valueOf(etSheets.getText());
        String unit = String.valueOf(etUnit.getText());
        String size = String.valueOf(etSize.getText());
        String remark = String.valueOf(etRemarks.getText());
        String productName = String.valueOf(etProductName.getText());


        if (TextUtils.isEmpty(dateOfProduct)) {

            etDatePurchase.setError("Enter Date of Product");
            return false;
        } else if (TextUtils.isEmpty(qty)) {

            etQunatity.setError("Enter Quantity");
            return false;

        } else if (TextUtils.isEmpty(amount)) {

            etAmount.setError("Enter Amount");
            return false;

        } else if (TextUtils.isEmpty(productName)) {

            etProductName.setError("Enter Product Name");
            return false;

        } else if (TextUtils.isEmpty(unit)) {

            etUnit.setError("Enter Unit");
            return false;

        } else if (TextUtils.isEmpty(transactionId)) {

            UtilityMethods.showToast(RetailerTranscationDetailActivity.this, "Unable to find transaction");
            return false;

        } else if (TextUtils.isEmpty(size)) {

            etSize.setError("Enter Size");
            return false;
        }


        if (unit.equalsIgnoreCase("r.f") || unit.equalsIgnoreCase("r.m")) {

            if (TextUtils.isEmpty(sheets)) {

                etSheets.setError("Enter Sheets");
                return false;

            }

        }


        return true;
    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        switch (response) {

            case ADD_TRANSACTION_STATUS:

                UtilityMethods.showToast(RetailerTranscationDetailActivity.this, "Status has been updated successfully");
                onBackPressed();
                break;

            case RETAILER_TRANSACTIONS_DETAIL:

                AddProductModel addProductModel = (AddProductModel) body;

                if (addProductModel != null) {

                    etAmount.setText(addProductModel.getAmount());
                    etDatePurchase.setText(addProductModel.getDateOfProduct());
                    etProductName.setText(addProductModel.getProductName());
                    etQunatity.setText(addProductModel.getQuantity());
                    etSheets.setText(addProductModel.getSheets());
                    etUnit.setText(addProductModel.getUnit());
                    etSize.setText(addProductModel.getSize());

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


    public void addProductStatus(AddProductModel addProductModel, String status) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.ADD_TRANSACTION_STATUS, addProductModel, status, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getTransactionDetail(String transactionId) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.RETAILER_TRANSACTIONS_DETAIL, transactionId, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


}

