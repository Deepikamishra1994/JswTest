package com.loyality.jsw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionHistoryDetail extends AppCompatActivity {
    TranscationModel transcationModel;
    @BindView(R.id.tvTranscationId)
    AppCompatTextView tvTranscationId;
    @BindView(R.id.tvDate)
    AppCompatTextView tvDate;
    @BindView(R.id.tvProduct)
    AppCompatTextView tvProduct;
    @BindView(R.id.tvSize)
    AppCompatTextView tvSize;
    @BindView(R.id.tvQunatity)
    AppCompatTextView tvQunatity;
    @BindView(R.id.tvSheets)
    AppCompatTextView tvSheets;
    @BindView(R.id.llSheets)
    LinearLayout llSheets;
    @BindView(R.id.tvUnit)
    AppCompatTextView tvUnit;
    @BindView(R.id.tvAmount)
    AppCompatTextView tvAmount;
    @BindView(R.id.tvRetailer)
    AppCompatTextView tvRetailer;
    @BindView(R.id.tvStatus)
    AppCompatTextView tvStatus;
    @BindView(R.id.llRoot)
    LinearLayout llRoot;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history_detail);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null)
            transcationModel = (TranscationModel) getIntent().getSerializableExtra("transactionHistory");
        tvTitle.setText("Transaction Details");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        setValues();
    }


    private void setValues() {
        tvTranscationId.setText(transcationModel.getTransactionId());
        tvAmount.setText(transcationModel.getAmount());
        tvDate.setText(transcationModel.getDate());
        tvProduct.setText(transcationModel.getProduct());
        tvQunatity.setText(transcationModel.getQuantity());
        tvSize.setText(transcationModel.getSize());
        tvUnit.setText(transcationModel.getUnit());
        tvSheets.setText(transcationModel.getSheets());
        tvStatus.setText(transcationModel.getStatus());
        tvRetailer.setText(transcationModel.getRetailer());

        if (!TextUtils.isEmpty(transcationModel.getStatus())) {

            switch (transcationModel.getStatus()) {

                case "PENDING":

                    tvStatus.setTextColor(getResources().getColor(R.color.colorOrange));

                    break;

                case "APRROVED":

                    tvStatus.setTextColor(getResources().getColor(R.color.colorGreen));

                    break;
                case "REJECTED":

                    tvStatus.setTextColor(getResources().getColor(R.color.colorRed));

                    break;
            }
        }
        if (TextUtils.isEmpty(transcationModel.getSheets())) {

            llSheets.setVisibility(View.GONE);
        } else {

            llSheets.setVisibility(View.VISIBLE);
            tvSheets.setText(transcationModel.getSheets());

        }

    }
}
