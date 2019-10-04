package com.loyality.jsw;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.adapters.CityAdapter;
import com.loyality.jsw.adapters.DistrictAdapter;
import com.loyality.jsw.adapters.StatesAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.CityModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewRetailerActivity extends AppCompatActivity implements PostDispatchs, GetDispatchs {

    String state, city,district;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.spState)
    AppCompatSpinner spState;
    @BindView(R.id.spDistrict)
    AppCompatSpinner spDistrict;
    @BindView(R.id.spCity)
    AppCompatSpinner spCity;
    @BindView(R.id.etName)
    AppCompatEditText etName;
    @BindView(R.id.etMobile)
    AppCompatEditText etMobile;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    RetailerModel retailerModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_retailer);
        ButterKnife.bind(this);
        tvTitle.setText("Add Retailer");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {


                    addRetailer(retailerModel);
                }
            }
        });
        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    StateModel stateModel = (StateModel) spState.getAdapter().getItem(position);

                    state = stateModel.getState();
                    getDistrict(stateModel.getState());

                } else {


                    state = "";
                    city = "";
                    spCity.setAdapter(null);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if(position>0){


                    DistrictModel districtModel = (DistrictModel) spDistrict.getAdapter().getItem(position);
                    district = districtModel.getDistrict();

                }
                else{
                    district="";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0){

                    CityModel cityModel = (CityModel) spCity.getAdapter().getItem(position);
                    city = cityModel.getCity();

                }else{

                    city = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getStates();
    }

    public void addRetailer(RetailerModel retailerModel) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.ADD_RETAILER, retailerModel, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    public boolean validate() {

          String name = String.valueOf(etName.getText());
          String mobile = String.valueOf(etMobile.getText());

        if (TextUtils.isEmpty(state)) {

            UtilityMethods.showToast(AddNewRetailerActivity.this, "Select State");
            return false;
        } else if (TextUtils.isEmpty(city)) {
            UtilityMethods.showToast(AddNewRetailerActivity.this, "Select City");
            return false;
        }
        else if (TextUtils.isEmpty(district)) {
            UtilityMethods.showToast(AddNewRetailerActivity.this, "Select District");
            return false;
        }
        else if (TextUtils.isEmpty(name)) {

            etName.setError("Enter Name");
            return false;
        }

        else if (TextUtils.isEmpty(mobile)) {

            etMobile.setError("Enter Mobile No.");
            return false;
        }


        retailerModel = new RetailerModel();
        retailerModel.setCity(city);
        retailerModel.setDistrict(String.valueOf(district));
        retailerModel.setState(state);
        retailerModel.setName(name);
        retailerModel.setMobileNo(mobile);

        return true;
    }

    public void getStates() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.STATES, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getDistrict(String id) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.DISTRICT, id, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getCities(String id) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.CITIES, id, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response) {

            case ADD_RETAILER:

                ResponseModel responseModel = (ResponseModel) body;
                UtilityMethods.showToast(AddNewRetailerActivity.this, responseModel.getSuccess());

                onBackPressed();
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
                else{

                    state = "";
                    spState.setAdapter(null);

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
                else{

                    district = "";
                    spDistrict.setAdapter(null);

                }

                getCities(state);

                break;

            case CITIES:


                List<CityModel> cityModels = (List<CityModel>) body;

                Log.e("testing","called");

                if (cityModels != null && cityModels.size() > 0) {


                    List<CityModel> finalCityModels = new ArrayList<>();
                    CityModel cityModel = new CityModel();
                    cityModel.setCity("Select City");
                    finalCityModels.add(cityModel);
                    finalCityModels.addAll(cityModels);
                    setCityAdapter(finalCityModels);

                }
                else{

                    Log.e("testing","called");
                    city = "";
                    spCity.setAdapter(null);

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

    public void setStateAdapter(List<StateModel> stateModels) {
        spState.setAdapter(new StatesAdapter(AddNewRetailerActivity.this, stateModels));

    }

    public void setCityAdapter(List<CityModel> cityModels) {
        spCity.setAdapter(new CityAdapter(AddNewRetailerActivity.this, cityModels));

    }
    public void setDistrictAdapter(List<DistrictModel> cityModels) {
        spDistrict.setAdapter(new DistrictAdapter(AddNewRetailerActivity.this, cityModels));

    }
}
