package com.loyality.jsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.loyality.jsw.activity.TMHomeActivity;
import com.loyality.jsw.common.Prefrences;
import com.loyality.jsw.common.UtilityMethods;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(TextUtils.isEmpty(Prefrences.getInstance().getToken(SplashActivity.this))){

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
            },3000);

        }
        else{

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                  if(!TextUtils.isEmpty(Prefrences.getInstance().getPartnerType(SplashActivity.this))){


                      if(Prefrences.getInstance().getPartnerType(SplashActivity.this).equalsIgnoreCase("FABRICATOR")){

                          startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                          overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                          finish();



                      }
                      else if(Prefrences.getInstance().getPartnerType(SplashActivity.this).equalsIgnoreCase("RETAILER")){

                          startActivity(new Intent(SplashActivity.this, RetailerHomeActivity.class));
                          overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                          finish();

                      }
                      else if(Prefrences.getInstance().getPartnerType(SplashActivity.this).equalsIgnoreCase("DISTRIBUTOR")){
                          startActivity(new Intent(SplashActivity.this, DistributorHomeActivity.class));
                          overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                          finish();



                      }
                      else if(Prefrences.getInstance().getPartnerType(SplashActivity.this).equalsIgnoreCase("TM")){
                          startActivity(new Intent(SplashActivity.this, TMHomeActivity.class));
                          overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                          finish();



                      }


                  }else{

                      startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                      overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                      finish();

                  }

                }
            },3000);


        }


    }
}
