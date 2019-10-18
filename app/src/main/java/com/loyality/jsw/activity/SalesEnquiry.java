package com.loyality.jsw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.Listerners.CheckDetailsListerner;
import com.loyality.jsw.R;
import com.loyality.jsw.adapters.EqualSpacingItemDecoration;
import com.loyality.jsw.adapters.TMSalesEnquiryAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.ApproveFabricator;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesEnquiry extends AppCompatActivity implements  GetDispatchs, PostDispatchs, TMSalesEnquiryAdapter.QueryStatusListerner {

    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.rv_approveFabricator)
    RecyclerView rvApproveFabricator;
    List<AddProductModel> addProductModelList = new ArrayList<>();
    @BindView(R.id.spSort)
    AppCompatSpinner spSort;
    @BindView(R.id.tv_sort)
    AppCompatTextView tvSort;
    @BindView(R.id.spSortBystatus)
    AppCompatSpinner spSortBystatus;
    @BindView(R.id.tvNoDataFound)
    AppCompatTextView tvNoDataFound;String  sortName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_fabricator_acrivity);
        ButterKnife.bind(this);
        tvTitle.setText("Sales Enquiry");
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
        addProductModel.setDateOfProduct("jgkbhgfkjbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbnf");
        addProductModelList.add(addProductModel);
        spSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 sortName = (String) spSort.getAdapter().getItem(position);
                if (sortName.equalsIgnoreCase("pending"))
                    getQuery("OPEN");
                else if (sortName.equalsIgnoreCase("Acknowledge"))
                    getQuery("CLOSED");
                else
                    getQuery("ALL");
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void getQuery(String sortName) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.SALES_QUERY, sortName, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    public void setAdapter(List<EnquiryModel> salesQueriesList) {

        TMSalesEnquiryAdapter addProductAdapter = new TMSalesEnquiryAdapter(SalesEnquiry.this, salesQueriesList, this);
        rvApproveFabricator.setLayoutManager(new LinearLayoutManager(this));
        rvApproveFabricator.setAdapter(addProductAdapter);

    }



    @Override
    public void apiSuccess(Object body, ResponseTypes response) {
        switch (response) {
            case SALES_QUERY:
            case APPROVE_FABRICATOR:
                List<EnquiryModel> salesEnquiries = (List<EnquiryModel>) body;

                if (salesEnquiries != null && salesEnquiries.size() > 0) {

                    setAdapter(salesEnquiries);

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

    @Override
    public void onItemClick(EnquiryModel enquiryModel) {
        Intent intent = new Intent(SalesEnquiry.this, TmSalesEnquiryDetailsActivity.class);
        intent.putExtra("enquiryModel",(Serializable) enquiryModel);
        startActivityForResult(intent,100);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==100)
        {
            if (sortName.equalsIgnoreCase("pending"))
                getQuery("OPEN");
            else if (sortName.equalsIgnoreCase("Acknowledge"))
                getQuery("CLOSED");
            else
                getQuery("ALL");
        }
    }
}
