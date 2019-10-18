package com.loyality.jsw.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.Constants.ApplicationConstants;
import com.loyality.jsw.Constants.DatePickerFragment;
import com.loyality.jsw.R;
import com.loyality.jsw.adapters.CustomRetailerListAdapter;
import com.loyality.jsw.adapters.DistrictAdapter;
import com.loyality.jsw.adapters.RetailersAdapter;
import com.loyality.jsw.adapters.StatesAdapter;
import com.loyality.jsw.adapters.TMPurchaseVerificationAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseVerificationActivity extends AppCompatActivity implements GetDispatchs, PostDispatchs, TMPurchaseVerificationAdapter.StatusListerner {

    @BindView(R.id.spRetailerState)
    AppCompatSpinner spRetailerState;
    @BindView(R.id.spRetailerDistrict)
    AppCompatSpinner spRetailerDistrict;
    @BindView(R.id.tv_retrailer)
    AppCompatAutoCompleteTextView tvRetrailer;
    @BindView(R.id.spRetailerSort)
    AppCompatSpinner spRetailerSort;
    @BindView(R.id.rlHistory)
    RecyclerView rlHistory;
    String state, city, district, retailer="";
    static List<RetailerModel> retailerModelsList = new ArrayList<>();
    List<AddProductModel> addProductModelList = new ArrayList<>();

    @BindView(R.id.spRetailer)
    AppCompatSpinner spRetailer;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.tvNoDataFound)
    AppCompatTextView tvNoDataFound;
    String sortName="",sortwithoutSpace="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_verification);
        ButterKnife.bind(this);
        ButterKnife.bind(this);
        tvTitle.setText("Purchase Verification");
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
        getStates();

        tvRetrailer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    ApplicationConstants.openSoftKeyboard(tvRetrailer, PurchaseVerificationActivity.this);
                }


            }
        });
        Log.e("retailer list size", String.valueOf(retailerModelsList.size()));
        CustomRetailerListAdapter adapter = new CustomRetailerListAdapter(this,
                R.layout.retailer_list_item, retailerModelsList);
        tvRetrailer.setAdapter(adapter);
        ;


        tvRetrailer.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                CustomRetailerListAdapter adapter = new CustomRetailerListAdapter(PurchaseVerificationActivity.this,
                        R.layout.retailer_list_item, retailerModelsList);
                tvRetrailer.setThreshold(1);
                tvRetrailer.setAdapter(adapter);

            }
        });


        tvRetrailer.setOnItemClickListener(onItemClickListener);
        spRetailerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 sortName = (String) spRetailerSort.getAdapter().getItem(position);
                if (retailer.equalsIgnoreCase(""))
                    UtilityMethods.showToast(PurchaseVerificationActivity.this, "Please select Retailer");
                else {
                    sortwithoutSpace = sortName.replaceAll("\\s", "");

                    getPurchaseList(sortwithoutSpace, "RET0003");
                   // getPurchaseList(sortwithoutSpace, retailer);

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spRetailerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    StateModel stateModel = (StateModel) spRetailerState.getAdapter().getItem(position);

                    state = stateModel.getState();
                    getDistrict(stateModel.getState());

                } else {


                    state = "";
                    district = "";
                    retailer = "";
                    spRetailerDistrict.setAdapter(null);
                    // spRetailer.setAdapter(null);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spRetailerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0) {


                    DistrictModel districtModel = (DistrictModel) spRetailerDistrict.getAdapter().getItem(position);
                    district = districtModel.getDistrict();
                    getAllRetailers(district);
                } else {
                    retailer = "";
                    spRetailer.setAdapter(null);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        getAllRetailers(district);

    }


    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    RetailerModel retailerModel = (RetailerModel) adapterView.getItemAtPosition(i);
                    retailer = retailerModel.getRetailerId();
                    if (spRetailer.getAdapter() != null) {

                        spRetailer.setSelection(0);
                      //  getPurchaseList(sortwithoutSpace, "RET0003");
                        getPurchaseList(sortwithoutSpace, retailer);


                    }

                    //   tvRetrailer.setText(retailerModel.getRetailerName());

/*
                     if(spRetailer.getAdapter()!=null){

                         spRetailer.setSelection(0);

                     }
*/


                }
            };

    public void getDistrict(String id) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.DISTRICT, id, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void setAdapter(List<TranscationModel> fabricatorPurchasesList) {

        TMPurchaseVerificationAdapter addProductAdapter = new TMPurchaseVerificationAdapter(PurchaseVerificationActivity.this, fabricatorPurchasesList, this);
        rlHistory.setLayoutManager(new LinearLayoutManager(this));
     //   rlHistory.addItemDecoration(new EqualSpacingItemDecoration(18));
        rlHistory.setAdapter(addProductAdapter);

    }

    public void setRetailerAdapter(List<RetailerModel> retailerModels) {
        spRetailer.setAdapter(new RetailersAdapter(PurchaseVerificationActivity.this, retailerModels));

    }

    public void getStates() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.STATES, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        switch (response) {


            case RETAILERS:

                List<RetailerModel> retailerModels = (List<RetailerModel>) body;
                retailerModelsList = retailerModels;

                if (retailerModels != null && retailerModels.size() > 0) {


                    List<RetailerModel> finalRetailerModel = new ArrayList<>();
                    RetailerModel retailerModel = new RetailerModel();
                    retailerModel.setiSalesCode("");
                    retailerModel.setRetailerName("Select Retailer");
                    finalRetailerModel.add(retailerModel);
                    finalRetailerModel.addAll(retailerModels);

                    setRetailerAdapter(finalRetailerModel);
                } else {

                    retailer = "";
                    spRetailer.setAdapter(null);
                }


                break;
            case STATES:

                List<StateModel> stateModels = (List<StateModel>) body;
                if (stateModels != null && stateModels.size() > 0) {


                    List<StateModel> finalStateModels = new ArrayList<>();
                    StateModel stateModel = new StateModel();
                    stateModel.setState("Select State");
                    finalStateModels.add(stateModel);
                    finalStateModels.addAll(stateModels);

                    setStateAdapter(finalStateModels);
                }

                break;


            case DISTRICT:


                List<DistrictModel> districtModels = (List<DistrictModel>) body;

                if (districtModels != null && districtModels.size() > 0) {


                    List<DistrictModel> finalDistrictModels = new ArrayList<>();
                    DistrictModel districtModel = new DistrictModel();
                    districtModel.setDistrict("Select District");
                    finalDistrictModels.add(districtModel);
                    finalDistrictModels.addAll(districtModels);
                    setDistrictAdapter(finalDistrictModels);

                }

                //  getCities(state);

                break;

            case FABRICATOR_PURCHASE:
                List<TranscationModel> fabricatorPurchases = (List<TranscationModel>) body;

                if (fabricatorPurchases != null && fabricatorPurchases.size() > 0) {

                    setAdapter(fabricatorPurchases);

                    tvNoDataFound.setVisibility(View.GONE);
                    rlHistory.setVisibility(View.VISIBLE);
                } else {

                    rlHistory.setAdapter(null);
                    tvNoDataFound.setVisibility(View.VISIBLE);
                    rlHistory.setVisibility(View.GONE);

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

    public void getPurchaseList(String retrailerId, String sortName) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.FABRICATOR_PURCHASE, sortName, retrailerId, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void setStateAdapter(List<StateModel> stateModels) {
        spRetailerState.setAdapter(new StatesAdapter(PurchaseVerificationActivity.this, stateModels));

    }

    public void setDistrictAdapter(List<DistrictModel> cityModels) {
        spRetailerDistrict.setAdapter(new DistrictAdapter(PurchaseVerificationActivity.this, cityModels));

    }


    public void getAllRetailers(String district) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.RETAILERS, district, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getTranscation(String sortName) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.RETAILER_TRANSACTIONS, "", sortName, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        getStates();
//    }




    @Override
    public void accept(TranscationModel transcationModel) {
        Intent intent= new Intent(PurchaseVerificationActivity.this, PurchaseVerificationDetailsActivity.class);
        intent.putExtra("transactionId",transcationModel.getTransactionId());
        startActivityForResult(intent,100);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==100)
        {
           getPurchaseList(retailer,sortwithoutSpace);
        }
        else
            getStates();
    }
}

