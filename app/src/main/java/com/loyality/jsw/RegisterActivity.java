package com.loyality.jsw;

import android.content.Intent;
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

import com.loyality.jsw.Constants.DatePickerFragment;
import com.loyality.jsw.adapters.CityAdapter;
import com.loyality.jsw.adapters.CommonAdapter;
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
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements PostDispatchs, GetDispatchs {

    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.etFirstName)
    AppCompatEditText etFirstName;
    @BindView(R.id.etLastName)
    AppCompatEditText etLastName;
    @BindView(R.id.etMobile)
    AppCompatEditText etMobile;
    @BindView(R.id.etEmergencyNo)
    AppCompatEditText etEmergencyNo;
    @BindView(R.id.etDob)
    AppCompatEditText etDob;

    @BindView(R.id.spState)
    AppCompatSpinner spState;
    @BindView(R.id.spCity)
    AppCompatSpinner spCity;
    @BindView(R.id.etTalik)
    AppCompatEditText etTalik;
    @BindView(R.id.etAddress)
    AppCompatEditText etAddress;
    @BindView(R.id.etPincode)
    AppCompatEditText etPincode;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    RegisterModel registerModel;


    String state, city, district, bloodGroup;
    @BindView(R.id.spDistrict)
    AppCompatSpinner spDistrict;
    @BindView(R.id.spBloodGroup)
    AppCompatSpinner spBloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("title");
        if(!TextUtils.isEmpty(title)){

            tvTitle.setText(title);
        }

        ArrayList<String> bloodlist = new ArrayList<>();
        bloodlist.add("Select Blood Group");
        bloodlist.add("NA");
        bloodlist.add("A+");
        bloodlist.add("O+");
        bloodlist.add("B+");
        bloodlist.add("AB+");
        bloodlist.add("A-");
        bloodlist.add("O-");
        bloodlist.add("B-");
        bloodlist.add("AB-");

        setBloodGroupAdapter(bloodlist);

        registerModel = new RegisterModel();
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

                    submitFabricator(registerModel);
                }
            }
        });

        spBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    bloodGroup = (String) spBloodGroup.getAdapter().getItem(position);


                } else {

                    bloodGroup= "";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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


                if (position > 0) {


                    DistrictModel districtModel = (DistrictModel) spDistrict.getAdapter().getItem(position);
                    district = districtModel.getDistrict();

                } else {
                    district = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    CityModel cityModel = (CityModel) spCity.getAdapter().getItem(position);
                    city = cityModel.getCity();

                } else {

                    city = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etDob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    showDatePickerDialog(v);
                }


            }
        });
        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        getStates();

    }

    public void showDatePickerDialog(View v) {

        DatePickerFragment newFragment = new DatePickerFragment(v, true);
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public void submitFabricator(RegisterModel registerModel) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.REGISTER, registerModel, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

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


    public boolean validate() {

        if (TextUtils.isEmpty(etFirstName.getText())) {

            etFirstName.setError("Enter First Name");
            return false;
        } else if (TextUtils.isEmpty(etLastName.getText())) {

            etLastName.setError("Enter Last Name");

            return false;
        } else if (TextUtils.isEmpty(etMobile.getText())) {
            etMobile.setError("Enter Mobile No.");

            return false;
        } else if (TextUtils.isEmpty(etEmergencyNo.getText())) {
            etEmergencyNo.setError("Enter Emergency No.");

            return false;
        } else if (TextUtils.isEmpty(etDob.getText())) {

            etDob.setError("Enter Dob");

            return false;
        } else if (TextUtils.isEmpty(bloodGroup)) {

            UtilityMethods.showToast(RegisterActivity.this, "Select Blood Group");

            return false;
        } else if (TextUtils.isEmpty(etAddress.getText())) {

            etAddress.setError("Enter Address");


            return false;
        } else if (TextUtils.isEmpty(etPincode.getText())) {

            etPincode.setError("Enter Pincode");

            return false;
        } else if (TextUtils.isEmpty(state)) {

            UtilityMethods.showToast(RegisterActivity.this, "Select State");
            return false;
        } else if (TextUtils.isEmpty(city)) {
            UtilityMethods.showToast(RegisterActivity.this, "Select City");

            return false;
        } else if (TextUtils.isEmpty(district)) {

            UtilityMethods.showToast(RegisterActivity.this, "Select District");
            return false;
        } else if (String.valueOf(etMobile.getText()).length() != 10) {


            etMobile.setError("Incorrect MobileNo.");
            return false;
        } else if (String.valueOf(etPincode.getText()).length() != 6) {


            etPincode.setError("Incorrect Pin code");
            return false;
        }
        else if (String.valueOf(etEmergencyNo.getText()).length() != 10) {


            etEmergencyNo.setError("Incorrect Emergency No.");
            return false;
        }

        registerModel.setFirstName(String.valueOf(etFirstName.getText()));
        registerModel.setLastName(String.valueOf(etLastName.getText()));
        registerModel.setMobileNo(String.valueOf(etMobile.getText()));
        registerModel.setEmergencyNo(String.valueOf(etEmergencyNo.getText()));
        registerModel.setDob(String.valueOf(etDob.getText()));
        registerModel.setBloodGroup(bloodGroup);
        registerModel.setState(String.valueOf(state));
        registerModel.setCity(String.valueOf(city));
        registerModel.setTaluka(String.valueOf(etTalik.getText()));
        registerModel.setDistrict(String.valueOf(district));
        registerModel.setAddress(String.valueOf(etAddress.getText()));
        registerModel.setPincode(String.valueOf(etPincode.getText()));


        return true;
    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response) {

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
                else{

                    district = "";
                    spDistrict.setAdapter(null);
                }


                getCities(state);

                break;

            case CITIES:


                List<CityModel> cityModels = (List<CityModel>) body;


                if (cityModels != null && cityModels.size() > 0) {


                    List<CityModel> finalCityModels = new ArrayList<>();
                    CityModel cityModel = new CityModel();
                    cityModel.setCity("Select City");
                    finalCityModels.add(cityModel);
                    finalCityModels.addAll(cityModels);
                    setCityAdapter(finalCityModels);

                }
                else{

                    city = "";
                    spCity.setAdapter(null);
                }

                break;

            case REGISTER:


                ResponseModel registerModel = (ResponseModel) body;
                Intent intent = new Intent(RegisterActivity.this, ThankyouActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(intent);
                finish();

                break;


        }

    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }

    public void setBloodGroupAdapter(List<String> stateModels) {
        spBloodGroup.setAdapter(new CommonAdapter(RegisterActivity.this, stateModels));

    }

    public void setStateAdapter(List<StateModel> stateModels) {
        spState.setAdapter(new StatesAdapter(RegisterActivity.this, stateModels));

    }

    public void setCityAdapter(List<CityModel> cityModels) {
        spCity.setAdapter(new CityAdapter(RegisterActivity.this, cityModels));

    }

    public void setDistrictAdapter(List<DistrictModel> cityModels) {
        spDistrict.setAdapter(new DistrictAdapter(RegisterActivity.this, cityModels));

    }
}
