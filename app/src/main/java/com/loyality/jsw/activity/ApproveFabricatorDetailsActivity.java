package com.loyality.jsw.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;

import com.loyality.jsw.Constants.ApprovalStatus;
import com.loyality.jsw.Constants.DatePickerFragment;
import com.loyality.jsw.R;
import com.loyality.jsw.adapters.CityAdapter;
import com.loyality.jsw.adapters.CommonAdapter;
import com.loyality.jsw.adapters.DistrictAdapter;
import com.loyality.jsw.adapters.StatesAdapter;
import com.loyality.jsw.common.Prefrences;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.ApproveFabricator;
import com.loyality.jsw.serverrequesthandler.models.CityModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApproveFabricatorDetailsActivity extends AppCompatActivity implements GetDispatchs, PostDispatchs {


    List<CityModel> finalCityModels = new ArrayList<>();
    List<DistrictModel> finalDistrictModels = new ArrayList<>();
    List<StateModel> finalStateModels = new ArrayList<>();

    String bloodGroup;
    ArrayList<String> bloodlist;
    String state, city, district;
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
    @BindView(R.id.spBloodGroup)
    AppCompatSpinner spBloodGroup;
    @BindView(R.id.spState)
    AppCompatSpinner spState;
    @BindView(R.id.spDistrict)
    AppCompatSpinner spDistrict;
    @BindView(R.id.spCity)
    AppCompatSpinner spCity;
    @BindView(R.id.etTalik)
    AppCompatEditText etTalik;
    @BindView(R.id.etAddress)
    AppCompatEditText etAddress;
    @BindView(R.id.etPincode)
    AppCompatEditText etPincode;
    @BindView(R.id.tvAccept)
    AppCompatTextView tvAccept;
    @BindView(R.id.tv_Reject)
    AppCompatTextView tvReject;
    @BindView(R.id.tv_OnHold)
    AppCompatTextView tvOnHold;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;String status, transactionId;
ApproveFabricator approveFabricator;
    RegisterModel registerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_fabricator_details);
        ButterKnife.bind(this);

        tvTitle.setText("Approve Frabricator");
        etMobile.setEnabled(false);
        etMobile.setClickable(false);
        etMobile.setFocusable(false);

        bloodlist = new ArrayList<>();
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
        if (getIntent().getExtras() != null)
            approveFabricator = (ApproveFabricator) getIntent().getSerializableExtra("transactionHistory");

            setData();
        status = approveFabricator.getStatus();
        if (!TextUtils.isEmpty(status)) {

            if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.REJECTED))) {

                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.GONE);
                tvReject.setVisibility(View.GONE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.APPROVED))) {

                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.GONE);
                tvReject.setVisibility(View.GONE);

            }

            else if (status.equalsIgnoreCase("ON-HOLD")) {
                tvOnHold.setVisibility(View.GONE);
                tvAccept.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.VISIBLE);


            }

            else if (status.equalsIgnoreCase(String.valueOf(ApprovalStatus.PENDING))) {


                tvOnHold.setVisibility(View.VISIBLE);
                tvAccept.setVisibility(View.VISIBLE);
                tvReject.setVisibility(View.VISIBLE);

            }



        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    submitFabricator(registerModel,"APPROVED");
                }

            }
        });
        tvOnHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    setDataInModel();

                    submitFabricator(registerModel,"ON_HOLD");
                }

            }
        });

        tvReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    setDataInModel();

                    submitFabricator(registerModel,"REJECTED");

                }

            }
        });




        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.e("Testf", "" + position);
                if (position > 0) {

                    StateModel stateModel = (StateModel) spState.getAdapter().getItem(position);

                    state = stateModel.getState();
                    getDistrict(stateModel.getState());

                } else {


                    state = "";
                    city = "";
                    district = "";
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
        spBloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    bloodGroup = (String) spBloodGroup.getAdapter().getItem(position);


                } else {

                    bloodGroup = "";

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

    private void setDataInModel() {
        registerModel= new RegisterModel();
        registerModel.setPartnerCode(approveFabricator.getPartnerCode());

          }

    private void setData() {
        etFirstName.setText(approveFabricator.getFirstName());
        etLastName.setText(approveFabricator.getLastName());
        etDob.setText(approveFabricator.getDob());
        etEmergencyNo.setText(approveFabricator.getEmergencyNo());
        etTalik.setText(approveFabricator.getTaluka());
        etMobile.setText(approveFabricator.getPartnerMobile());
        etPincode.setText(approveFabricator.getPincode());
        etAddress.setText(approveFabricator.getAddressLine1());
        //etPincode.setText(approveFabricator.etc();

        state = approveFabricator.getState();
        city = approveFabricator.getCity();
        district= approveFabricator.getDistrict();


    }

    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void submitFabricator(RegisterModel registerModel, String status) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");


            registerModel.setStatus(status);
            DispatchPostResponse.disptatchRequest(this, ResponseTypes.APPROVE_FABRICATOR, registerModel, null, this);

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


    public void getProfile() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.PROFILE_BY_ID, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {


        switch (response) {

            case STATES:

                List<StateModel> stateModels = (List<StateModel>) body;
                if (stateModels != null && stateModels.size() > 0) {


                    finalStateModels = new ArrayList<>();
                    StateModel stateModel = new StateModel();
                    stateModel.setState("Select State");
                    finalStateModels.add(stateModel);
                    finalStateModels.addAll(stateModels);

                    setStateAdapter(finalStateModels);
                }
               //getProfile();

                break;


            case APPROVE_FABRICATOR:
                UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this,"Status Successfully Updated");

                onBackPressed();
                break;

            case DISTRICT:


                List<DistrictModel> districtModels = (List<DistrictModel>) body;

                if (districtModels != null && districtModels.size() > 0) {


                    finalDistrictModels = new ArrayList<>();
                    DistrictModel districtModel = new DistrictModel();
                    districtModel.setDistrict("Select District");
                    finalDistrictModels.add(districtModel);
                    finalDistrictModels.addAll(districtModels);
                    setDistrictAdapter(finalDistrictModels);
                    for (int i = 0; i < finalDistrictModels.size(); i++) {


                        if (!TextUtils.isEmpty(district) && district.equalsIgnoreCase(finalDistrictModels.get(i).getDistrict())) {


                            spDistrict.setSelection(i);

                        }
                    }
                }

                getCities(state);

                break;

            case CITIES:


                List<CityModel> cityModels = (List<CityModel>) body;


                if (cityModels != null && cityModels.size() > 0) {


                    finalCityModels = new ArrayList<>();
                    CityModel cityModel = new CityModel();
                    cityModel.setCity("Select City");
                    finalCityModels.add(cityModel);
                    finalCityModels.addAll(cityModels);
                    setCityAdapter(finalCityModels);

                    for (int i = 0; i < finalCityModels.size(); i++) {


                        if (!TextUtils.isEmpty(city) && city.equalsIgnoreCase(finalCityModels.get(i).getCity())) {


                            spCity.setSelection(i);

                        }


                    }
                }

                for (int i = 0; i < finalStateModels.size(); i++) {

                    if (!TextUtils.isEmpty(state) && state.equalsIgnoreCase(finalStateModels.get(i).getState())) {


                        spState.setSelection(i);

                    }
                }

                for (int i = 0; i < bloodlist.size(); i++) {

                    if (bloodGroup.equalsIgnoreCase(bloodlist.get(i))) {

                        spBloodGroup.setSelection(i);
                    }

                }



                break;

            case PROFILE_BY_ID:


                registerModel = (RegisterModel) body;
                Prefrences.getInstance().storeUserProfile(ApproveFabricatorDetailsActivity.this, registerModel.getFirstName(), registerModel.getMobileNo(), "");

            /*    etRetailerId.setText(registerModel.getRetailerId());
                etTMASsingedId.setText(registerModel.getTmAssignedId());
                etTMMobile.setText(registerModel.getTmAssignedMobileNo());
                etTMName.setText(registerModel.getTmAssignedName());
                etTMName.setEnabled(false);
*/
                etFirstName.setText(registerModel.getFirstName());
                etDob.setText(registerModel.getDob());
                etLastName.setText(registerModel.getLastName());
                etMobile.setText(registerModel.getMobileNo());
                etPincode.setText(registerModel.getPincode());
                etTalik.setText(registerModel.getTaluka());
                etAddress.setText(registerModel.getAddress());
                city = registerModel.getCity();
                state = registerModel.getState();
                district = registerModel.getDistrict();
                etEmergencyNo.setText(registerModel.getEmergencyNo());


                bloodGroup = (registerModel.getBloodGroup());
                etPincode.setText(registerModel.getPincode());


                for (int i = 0; i < finalStateModels.size(); i++) {

                    if (!TextUtils.isEmpty(state) && state.equalsIgnoreCase(finalStateModels.get(i).getState())) {


                        spState.setSelection(i);

                    }
                }

                for (int i = 0; i < bloodlist.size(); i++) {

                    if (bloodGroup.equalsIgnoreCase(bloodlist.get(i))) {

                        spBloodGroup.setSelection(i);
                    }

                }


                break;

            case EDIT_PROFILE:

                UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this, "Profile Updated Successfully");
                onBackPressed();


                break;

        }


    }

    @Override
    public void apiError(ErrorDto error) {

    }

    @Override
    public void error(String body) {

    }


    public boolean validate() {

        if (TextUtils.isEmpty(etFirstName.getText())) {

            etFirstName.setError("Enter First Name");
            etFirstName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etLastName.getText())) {

            etLastName.setError("Enter Last Name");
            etLastName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etMobile.getText())) {
            etMobile.setError("Enter Mobile Name");
            etMobile.requestFocus();

            return false;
        } else if (TextUtils.isEmpty(etEmergencyNo.getText())) {
            etEmergencyNo.setError("Enter Emergency No.");
            etEmergencyNo.requestFocus();

            return false;
        } else if (TextUtils.isEmpty(etDob.getText())) {

            etDob.setError("Enter Dob");
            etDob.requestFocus();

            return false;
        } else if (TextUtils.isEmpty(bloodGroup)) {

            UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this, "Select Blood Group");

            return false;
        } else if (TextUtils.isEmpty(etAddress.getText())) {

            etAddress.setError("Enter Address");
            etAddress.requestFocus();


            return false;
        } else if (TextUtils.isEmpty(etPincode.getText())) {

            etPincode.setError("Enter Pincode");
            etPincode.requestFocus();

            return false;
        } else if (TextUtils.isEmpty(state)) {

            UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this, "Select State");
            return false;
        } /*else if (TextUtils.isEmpty(city)) {
            UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this, "Select City");

            return false;
        }*/ else if (TextUtils.isEmpty(district)) {

            UtilityMethods.showToast(ApproveFabricatorDetailsActivity.this, "Select District");
            return false;
        } else if (TextUtils.isEmpty(etPincode.getText())) {

            etPincode.setError("Enter Pin code");
            etPincode.requestFocus();
            return false;
        } else if (etEmergencyNo.getText().length() != 10) {

            etEmergencyNo.setError("Incorrect Emergency No.");
            etEmergencyNo.requestFocus();

            return false;

        } else if (etPincode.getText().toString().length() != 6) {
            etPincode.requestFocus();
            etPincode.setError("Incorrect Pin code");
            return false;


        }


        Log.e("Emggefeecd", "" + etEmergencyNo.getText().length());
        registerModel= new RegisterModel();

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
        registerModel.setPartnerCode(approveFabricator.getPartnerCode());


        return true;
    }

    public void setStateAdapter(List<StateModel> stateModels) {
        spState.setAdapter(new StatesAdapter(ApproveFabricatorDetailsActivity.this, stateModels));

    }

    public void setCityAdapter(List<CityModel> cityModels) {
        spCity.setAdapter(new CityAdapter(ApproveFabricatorDetailsActivity.this, cityModels));

    }

    public void setDistrictAdapter(List<DistrictModel> cityModels) {
        spDistrict.setAdapter(new DistrictAdapter(ApproveFabricatorDetailsActivity.this, cityModels));

    }


    public void setBloodGroupAdapter(List<String> stateModels) {
        spBloodGroup.setAdapter(new CommonAdapter(ApproveFabricatorDetailsActivity.this, stateModels));

    }

}
