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

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.ivProfile)
    CircleImageView ivProfile;
    @BindView(R.id.tvUserName)
    AppCompatTextView tvUserName;
    @BindView(R.id.tvUserMobile)
    AppCompatTextView tvUserMobile;
    @BindView(R.id.rlHeader)
    RelativeLayout rlHeader;
    @BindView(R.id.tvAddDealer)
    AppCompatTextView tvAddDealer;
    @BindView(R.id.llAddPurchase)
    LinearLayout llAddPurchase;
    @BindView(R.id.tvTranscationHistory)
    AppCompatTextView tvTranscationHistory;
    @BindView(R.id.llTranscationHistory)
    LinearLayout llTranscationHistory;
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
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.ivLogout)
    AppCompatImageButton ivLogout;
    @BindView(R.id.ivNotify)
    AppCompatImageButton ivNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        tvUserName.setText(Prefrences.getInstance().getPartnerName(HomeActivity.this));
        tvUserMobile.setText(Prefrences.getInstance().getPartnerMobile(HomeActivity.this));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Prefrences.getInstance().clearUserDetail(HomeActivity.this);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        llAddPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, AddProductActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        llActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, ActivitiesHistoryActivity.class));
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

                startActivity(new Intent(HomeActivity.this, EditProfileActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        llEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, EnquiryActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

        });

        llTranscationHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, TranscationHistoryActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}
