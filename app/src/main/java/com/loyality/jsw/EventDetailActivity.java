package com.loyality.jsw;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.EventModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity implements GetDispatchs {


    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.tvActivityName)
    AppCompatTextView tvActivityName;
    @BindView(R.id.tvActivityDate)
    AppCompatTextView tvActivityDate;
    @BindView(R.id.tvDescription)
    AppCompatTextView tvDescription;
    @BindView(R.id.tvDuration)
    AppCompatTextView tvDuration;
    @BindView(R.id.tvCriteria)
    AppCompatTextView tvCriteria;
    @BindView(R.id.tvReward)
    AppCompatTextView tvReward;
    @BindView(R.id.tvEventName)
    AppCompatTextView tvEventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);
        tvTitle.setText("Activity Detail");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        getEventDetail(getIntent().getStringExtra("id"));
    }

    public void getEventDetail(String id) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.EVENT_DETAIL, id, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        EventModel eventModel = (EventModel) body;

        if (eventModel != null) {


            tvActivityName.setText(eventModel.getEventName());
            tvActivityDate.setText(eventModel.getEventDate());
            tvDescription.setText(eventModel.getEventDescription());
            tvDuration.setText(eventModel.getSchemeDuration());
            tvCriteria.setText(eventModel.getWinningCriteria());
            tvReward.setText(eventModel.getRewardWon());


        }

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }


}
