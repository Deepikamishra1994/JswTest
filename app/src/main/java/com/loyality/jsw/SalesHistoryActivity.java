package com.loyality.jsw;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.adapters.EqualSpacingItemDecoration;
import com.loyality.jsw.adapters.SalesHistoryAdapter;
import com.loyality.jsw.adapters.SizeUnitsAdapter;
import com.loyality.jsw.adapters.TransHistoryAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesHistoryActivity extends AppCompatActivity implements GetDispatchs {

    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.spSort)
    AppCompatSpinner spSort;
    @BindView(R.id.rlHistory)
    RecyclerView rlHistory;
    @BindView(R.id.tvNoDataFound)
    AppCompatTextView tvNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation);
        ButterKnife.bind(this);

        tvTitle.setText("Verifications");

        List<String> statusList = new ArrayList<>();
        statusList.add("ALL");
        statusList.add("PENDING");
        statusList.add("APPROVED");
        statusList.add("REJECTED");
        spSort.setAdapter(new SizeUnitsAdapter(SalesHistoryActivity.this, statusList));


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String sortName = (String) spSort.getAdapter().getItem(position);

                getTranscation(sortName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //   getTranscation(sortName);
    }

    public void getTranscation(String sortName) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.TRANSACTIONS, sortName, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        List<TranscationModel> eventModelList = (List<TranscationModel>) body;

        if (eventModelList != null && eventModelList.size() > 0) {

            setAdapter(eventModelList);


            tvNoDataFound.setVisibility(View.GONE);
            rlHistory.setVisibility(View.VISIBLE);
        }
        else{

            rlHistory.setAdapter(null);
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


    public void setAdapter(List<TranscationModel> eventModelList) {

        if(rlHistory.getAdapter()!=null){

            rlHistory.getAdapter().notifyDataSetChanged();

        }else{

            SalesHistoryAdapter myHistoryAdapter = new SalesHistoryAdapter(SalesHistoryActivity.this, eventModelList);
            rlHistory.setLayoutManager(new LinearLayoutManager(this));
            rlHistory.addItemDecoration(new EqualSpacingItemDecoration(18));
            rlHistory.setAdapter(myHistoryAdapter);

        }

    }
}
