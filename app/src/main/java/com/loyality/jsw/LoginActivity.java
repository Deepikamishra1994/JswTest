package com.loyality.jsw;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.adapters.CommonAdapter;
import com.loyality.jsw.common.Prefrences;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.LoginModel;
import com.loyality.jsw.serverrequesthandler.models.LoginResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements PostDispatchs, GetDispatchs {

    @BindView(R.id.etEmployeeId)
    AppCompatEditText etEmployeeId;
    @BindView(R.id.etPassword)
    AppCompatEditText etPassword;
    @BindView(R.id.btnLogin)
    AppCompatButton btnLogin;
    @BindView(R.id.btnSignUp)
    AppCompatButton btnSignUp;
    LoginModel loginModel;
    @BindView(R.id.tvForgotPassword)
    AppCompatTextView tvForgotPassword;
    @BindView(R.id.spPartnerType)
    AppCompatSpinner spPartnerType;
    @BindView(R.id.tvOR)
    AppCompatTextView tvOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ArrayList<String> bloodlist = new ArrayList<>();
        bloodlist.add("Select Partner Type");
        bloodlist.add("Fabricator");
        bloodlist.add("Retailer");
        bloodlist.add("Distributor");


        setPartnerTypeAdapter(bloodlist);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validate()) {

                    loginMe(loginModel);
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


            }
        });

        spPartnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>1){

                    tvOR.setVisibility(View.GONE);
                    btnSignUp.setVisibility(View.GONE);

                }else{

                    tvOR.setVisibility(View.VISIBLE);
                    btnSignUp.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public boolean validate() {

        if (TextUtils.isEmpty(etEmployeeId.getText())) {

            etEmployeeId.setError("Enter User Id");
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Enter Password");
            return false;
        }


        loginModel = new LoginModel();
        loginModel.setPassword(String.valueOf(etPassword.getText()));
        loginModel.setUserId(String.valueOf(etEmployeeId.getText()));

        return true;
    }

    public void loginMe(LoginModel loginModel) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.LOGIN, loginModel, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getProfile() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.PROFILE, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response) {


            case LOGIN:

                LoginResponseModel loginResponseModel = (LoginResponseModel) body;
                Prefrences.getInstance().storeUserData(LoginActivity.this, "", loginResponseModel.getUserType(), loginResponseModel.getToken());
                getProfile();

                break;

            case PROFILE:


                RegisterModel registerModel = (RegisterModel) body;

                Prefrences.getInstance().storeUserProfile(LoginActivity.this, registerModel.getFirstName(), registerModel.getMobileNo(), "");

                if (Prefrences.getInstance().getPartnerType(LoginActivity.this).equalsIgnoreCase("FABRICATOR")) {

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();


                } else if (Prefrences.getInstance().getPartnerType(LoginActivity.this).equalsIgnoreCase("RETAILER")) {

                    startActivity(new Intent(LoginActivity.this, RetailerHomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();


                } else if (Prefrences.getInstance().getPartnerType(LoginActivity.this).equalsIgnoreCase("DISTRIBUTOR")) {
                    startActivity(new Intent(LoginActivity.this, DistributorHomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();


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

    public void setPartnerTypeAdapter(List<String> partnerTypeList) {
        spPartnerType.setAdapter(new CommonAdapter(LoginActivity.this, partnerTypeList));
    }
}
