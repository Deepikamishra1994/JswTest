package com.loyality.jsw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.Listerners.CheckDetailsListerner;
import com.loyality.jsw.R;
import com.loyality.jsw.adapters.ApproveFabricatorAdapter;
import com.loyality.jsw.adapters.EqualSpacingItemDecoration;
import com.loyality.jsw.adapters.SizeUnitsAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.ApproveFabricator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApproveFabricatorAcrivity extends AppCompatActivity implements CheckDetailsListerner, GetDispatchs {

    @BindView(R.id.rv_approveFabricator)
    RecyclerView rvApproveFabricator;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    List<AddProductModel> addProductModelList = new ArrayList<>();
    @BindView(R.id.spSort)
    AppCompatSpinner spSort;
    @BindView(R.id.tv_sort)
    AppCompatTextView tvSort;
    @BindView(R.id.spSortBystatus)
    AppCompatSpinner spSortBystatus;
    @BindView(R.id.tvNoDataFound)
    AppCompatTextView tvNoDataFound;String sortName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_fabricator_acrivity);
        ButterKnife.bind(this);
        tvTitle.setText("Approve Fabricator");
        spSort.setVisibility(View.GONE);
        tvSort.setVisibility(View.GONE);
        spSortBystatus.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        AddProductModel addProductModel = new AddProductModel();
        addProductModel.setAmount("abc");
        addProductModel.setProductId("443");
        addProductModel.setTransactionId("443");
        addProductModel.setDateOfProduct("443");
        addProductModelList.add(addProductModel);
        List<String> statusList = new ArrayList<>();
        statusList.add("ALL");
        statusList.add("PENDING");
        statusList.add("APPROVED");
        statusList.add("REJECTED");
        spSort.setAdapter(new SizeUnitsAdapter(ApproveFabricatorAcrivity.this, statusList));

        spSortBystatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                sortName = (String) spSortBystatus.getAdapter().getItem(position);

                getApprovefabricatorList(sortName);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getApprovefabricatorList(String sortName) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.APPROVE_FABRICATOR, sortName, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void setAdapter(List<ApproveFabricator> ApproveFabricatorList) {

        ApproveFabricatorAdapter addProductAdapter = new ApproveFabricatorAdapter(ApproveFabricatorAcrivity.this, ApproveFabricatorList, this);
        rvApproveFabricator.setLayoutManager(new LinearLayoutManager(this));
      //  rvApproveFabricator.addItemDecoration(new EqualSpacingItemDecoration(18));
        rvApproveFabricator.setAdapter(addProductAdapter);

    }

    @Override
    public void onItemClick(ApproveFabricator addProductModel) {
        Intent intent = new Intent(ApproveFabricatorAcrivity.this, ApproveFabricatorDetailsActivity.class);
        intent.putExtra("transactionHistory",(Serializable) addProductModel);
        startActivityForResult(intent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
           getApprovefabricatorList(sortName);
        }

    }

    @Override
    public void apiSuccess(Object body, ResponseTypes response) {
        switch (response) {
            case APPROVE_FABRICATOR:
                List<ApproveFabricator> approveFabricator = (List<ApproveFabricator>) body;

                if (approveFabricator != null && approveFabricator.size() > 0) {

                    setAdapter(approveFabricator);

                    tvNoDataFound.setVisibility(View.GONE);
                    rvApproveFabricator.setVisibility(View.VISIBLE);
                } else {

                    rvApproveFabricator.setAdapter(null);
                    tvNoDataFound.setVisibility(View.VISIBLE);
                    rvApproveFabricator.setVisibility(View.GONE);

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
}

