package com.loyality.jsw;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class TranscationHistoryActivity extends AppCompatActivity implements GetDispatchs {

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
    @BindView(R.id.tv_totalPurchase)
    AppCompatTextView tvTotalPurchase;
    @BindView(R.id.tv_totalApprove)
    AppCompatTextView tvTotalApprove;
    @BindView(R.id.tv_totalPending)
    AppCompatTextView tvTotalPending;
    @BindView(R.id.ll_summary)
    LinearLayout llSummary;
    @BindView(R.id.tl_header)
    TableLayout tlHeader;
    @BindView(R.id.cardView)
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation);
        ButterKnife.bind(this);

        tvTitle.setText("Transactions");

        List<String> statusList = new ArrayList<>();
        statusList.add("ALL");
        statusList.add("PENDING");
        statusList.add("APPROVED");
        statusList.add("REJECTED");
        spSort.setAdapter(new SizeUnitsAdapter(TranscationHistoryActivity.this, statusList));


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
            cardView.setVisibility(View.VISIBLE);
            tvTotalApprove.setText(eventModelList.get(0).getTotalApprove());
            tvTotalPending.setText(eventModelList.get(0).getTotalPending());
            tvTotalPurchase.setText(eventModelList.get(0).getTotalPurchase());
            setAdapter(eventModelList);
            rlHistory.setVisibility(View.VISIBLE);

            tvNoDataFound.setVisibility(View.GONE);
            tlHeader.setVisibility(View.VISIBLE);
        } else {

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


            TransHistoryAdapter myHistoryAdapter = new TransHistoryAdapter(TranscationHistoryActivity.this, eventModelList);
            rlHistory.setLayoutManager(new LinearLayoutManager(this));
            // rlHistory.addItemDecoration(new EqualSpacingItemDecoration(18));
            rlHistory.setAdapter(myHistoryAdapter);


    }
}
