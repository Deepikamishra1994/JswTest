package com.loyality.jsw.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.R;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TmSalesEnquiryDetailsActivity extends AppCompatActivity implements PostDispatchs {

    @BindView(R.id.tv_fabricatorId)
    AppCompatTextView tvFabricatorId;
    @BindView(R.id.tv_fabricatorName)
    AppCompatTextView tvFabricatorName;
    @BindView(R.id.tv_state)
    AppCompatTextView tvState;
    @BindView(R.id.tv_city)
    AppCompatTextView tvCity;
    @BindView(R.id.tv_query)
    AppCompatTextView tvQuery;
    @BindView(R.id.tv_submit)
    AppCompatTextView tvSubmit;
    @BindView(R.id.ll_summary)
    LinearLayout llSummary;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.et_remarks)
    AppCompatEditText etRemarks;
    EnquiryModel enquiryModel;
    @BindView(R.id.tv_status)
    AppCompatTextView tvStatus;
    @BindView(R.id.ck_read)
    AppCompatCheckBox ckRead;
    String infoRead = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_sales_enquiry_details);
        ButterKnife.bind(this);
        tvSubmit.setVisibility(View.VISIBLE);
        tvTitle.setText("Sales Enquiry Details");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        if (getIntent().getExtras() != null)
            enquiryModel = (EnquiryModel) getIntent().getSerializableExtra("enquiryModel");
        setData();
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enquiryModel.getStatus().equalsIgnoreCase("OPEN")) {
                    if (infoRead.equalsIgnoreCase(""))
                        UtilityMethods.showToast(TmSalesEnquiryDetailsActivity.this, "Please Tick I have read all the Details");
                    else if (etRemarks.getText().toString().equalsIgnoreCase(""))
                        UtilityMethods.showToast(TmSalesEnquiryDetailsActivity.this, "Please give remark");

                    else
                        submit();
                } else {
                    tvSubmit.setVisibility(View.GONE);
                    ckRead.setVisibility(View.GONE);

                }


            }
        });
        ckRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                              @Override
                                              public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                  if (isChecked)
                                                      infoRead = "read";
                                                  else
                                                      infoRead = "";

                                              }
                                          }
        );

    }


    private void submit() {


        if (UtilityMethods.isNetworkAvailable(this)) {
            if (enquiryModel != null) {
                enquiryModel.setRemarks(etRemarks.getText().toString());
                enquiryModel.setStatus("CLOSED");


                UtilityMethods.showProgressDialog(this, null, "Please Wait...");

                DispatchPostResponse.disptatchRequest(this, ResponseTypes.QUERYRESULT, enquiryModel, null, this);

            }


        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }
    }


    private void setData() {
        tvFabricatorId.setText("");
        tvFabricatorName.setText(enquiryModel.getFirstName() + enquiryModel.getLastName());
        tvState.setText(enquiryModel.getState());
        tvCity.setText(enquiryModel.getCity());
        tvQuery.setText(enquiryModel.getQuery());
        etRemarks.setText(enquiryModel.getRemarks());

        if (!TextUtils.isEmpty(enquiryModel.getStatus())) {

            switch (enquiryModel.getStatus()) {

                case "OPEN":
                    tvStatus.setTextColor(getResources().getColor(R.color.colorOrange));
                    tvStatus.setText("PENDING");

                    break;

                case "CLOSED":

                    tvStatus.setTextColor(getResources().getColor(R.color.colorGreen));
                    tvStatus.setText("ACKNOWLEDGE");
                    tvStatus.setVisibility(View.GONE);
                    ckRead.setVisibility(View.GONE);

                    break;

            }
        }
    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {
        switch (response) {
            case QUERYRESULT:

                UtilityMethods.showToast(TmSalesEnquiryDetailsActivity.this, "Status has been updated successfully");
                onBackPressed();
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
