package com.loyality.jsw;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.adapters.EqualSpacingItemDecoration;
import com.loyality.jsw.adapters.MyHistoryAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.EventModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitiesHistoryActivity extends AppCompatActivity implements GetDispatchs {


    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.rlHistory)
    RecyclerView rlHistory;
    @BindView(R.id.tvNoDataFound)
    AppCompatTextView tvNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_history);
        ButterKnife.bind(this);

        tvTitle.setText("Activities");


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        getEvents();
    }

    public void getEvents() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.EVENTS, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        List<EventModel> eventModelList = (List<EventModel>) body;

        if (eventModelList != null && eventModelList.size() > 0) {

            tvNoDataFound.setVisibility(View.GONE);
            rlHistory.setVisibility(View.VISIBLE);
            setAdapter(eventModelList);

        }
        else{

            tvNoDataFound.setVisibility(View.VISIBLE);
            rlHistory.setVisibility(View.GONE);
        }

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }


    public void setAdapter(List<EventModel> eventModelList) {

        MyHistoryAdapter myHistoryAdapter = new MyHistoryAdapter(ActivitiesHistoryActivity.this, eventModelList);
        rlHistory.setLayoutManager(new LinearLayoutManager(this));
        rlHistory.addItemDecoration(new EqualSpacingItemDecoration(18));
        rlHistory.setAdapter(myHistoryAdapter);

    }

}
