package com.loyality.jsw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.common.Prefrences;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RetailerHomeActivity extends AppCompatActivity {

    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.ivLogout)
    AppCompatImageButton ivLogout;
    @BindView(R.id.ivNotify)
    AppCompatImageButton ivNotify;
    @BindView(R.id.ivProfile)
    CircleImageView ivProfile;
    @BindView(R.id.tvUserName)
    AppCompatTextView tvUserName;
    @BindView(R.id.tvUserMobile)
    AppCompatTextView tvUserMobile;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.tvFarbricator)
    AppCompatTextView tvFarbricator;
    @BindView(R.id.llFabricatorNomination)
    LinearLayout llFabricatorNomination;
    @BindView(R.id.tvTranscationHistory)
    AppCompatTextView tvTranscationHistory;
    @BindView(R.id.llSales)
    LinearLayout llSales;
    @BindView(R.id.tvEditProfile)
    AppCompatTextView tvEditProfile;
    @BindView(R.id.llEditProfile)
    LinearLayout llEditProfile;
    @BindView(R.id.tvActivity)
    AppCompatTextView tvActivity;
    @BindView(R.id.llActivity)
    LinearLayout llActivity;
    @BindView(R.id.tvSalesEnquiry)
    AppCompatTextView tvSalesEnquiry;
    @BindView(R.id.llEnquiry)
    LinearLayout llEnquiry;
    @BindView(R.id.tvDownload)
    AppCompatTextView tvDownload;
    @BindView(R.id.llDownload)
    LinearLayout llDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_home);
        ButterKnife.bind(this);
        llEnquiry.setVisibility(View.VISIBLE);
        tvUserName.setText(Prefrences.getInstance().getPartnerName(RetailerHomeActivity.this));
        tvUserMobile.setText(Prefrences.getInstance().getPartnerMobile(RetailerHomeActivity.this));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Prefrences.getInstance().clearUserDetail(RetailerHomeActivity.this);
                Intent intent = new Intent(RetailerHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        llFabricatorNomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RetailerHomeActivity.this, RegisterActivity.class).putExtra("title","Nomination"));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        llActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RetailerHomeActivity.this, ActivitiesHistoryActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        llDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RetailerHomeActivity.this, RetailerDistributorEditProfileActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        llEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RetailerHomeActivity.this, EnquiryActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

        });

        llSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RetailerHomeActivity.this, RetailerTranscationActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
