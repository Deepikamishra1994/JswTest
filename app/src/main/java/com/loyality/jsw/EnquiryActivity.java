package com.loyality.jsw;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryActivity extends AppCompatActivity implements PostDispatchs, GetDispatchs {

    String state, city;
    EnquiryModel enquiryModel;
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.spState)
    AppCompatSpinner spState;
    @BindView(R.id.spCity)
    AppCompatSpinner spCity;
    @BindView(R.id.etQuery)
    AppCompatEditText etQuery;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        ButterKnife.bind(this);
        tvTitle.setText("Enquiry");

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


                    submitQuery(enquiryModel);
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
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DistrictModel districtModel = (DistrictModel) spCity.getAdapter().getItem(position);
                city = districtModel.getDistrict();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        getStates();
    }

    public void submitQuery(EnquiryModel enquiryModel) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.ENQUIRY, enquiryModel, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    public boolean validate() {

        if (TextUtils.isEmpty(state)) {

            UtilityMethods.showToast(EnquiryActivity.this, "Select State");
            return false;
        } else if (TextUtils.isEmpty(city)) {
            UtilityMethods.showToast(EnquiryActivity.this, "Select District");
            return false;
        } else if (TextUtils.isEmpty(etQuery.getText())) {

            etQuery.setError("Enter Query");
            return false;
        }


        enquiryModel = new EnquiryModel();
        enquiryModel.setCity(city);
        enquiryModel.setQuery(String.valueOf(etQuery.getText()));
        enquiryModel.setState(state);

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



    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response){

            case ENQUIRY:

                ResponseModel responseModel = (ResponseModel) body;
                UtilityMethods.showToast(EnquiryActivity.this, responseModel.getSuccess());
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

                break;


            case DISTRICT:


                List<DistrictModel> districtModels = (List<DistrictModel>) body;

                if (districtModels != null && districtModels.size() > 0) {

                    setCityAdapter(districtModels);
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
        spState.setAdapter(new StatesAdapter(EnquiryActivity.this, stateModels));

    }

    public void setCityAdapter(List<DistrictModel> cityModels) {
        spCity.setAdapter(new DistrictAdapter(EnquiryActivity.this, cityModels));

    }
}
