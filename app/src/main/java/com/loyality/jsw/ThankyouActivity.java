package com.loyality.jsw;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThankyouActivity extends AppCompatActivity {

    @BindView(R.id.ivBack)
    AppCompatImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        ButterKnife.bind(this);
       ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });

    }
}
