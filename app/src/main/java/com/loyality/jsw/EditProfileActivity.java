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
import androidx.fragment.app.DialogFragment;

import com.loyality.jsw.Constants.DatePickerFragment;
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
import com.loyality.jsw.serverrequesthandler.models.CityModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileActivity extends AppCompatActivity implements GetDispatchs, PostDispatchs {

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
    @BindView(R.id.spDistrict)
    AppCompatSpinner spDistrict;
    @BindView(R.id.etAddress)
    AppCompatEditText etAddress;
    @BindView(R.id.etPincode)
    AppCompatEditText etPincode;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    RegisterModel registerModel;
    String state, city, district;
    @BindView(R.id.etRetailerId)
    AppCompatEditText etRetailerId;
    @BindView(R.id.etTMASsingedId)
    AppCompatEditText etTMASsingedId;
    @BindView(R.id.etTMMobile)
    AppCompatEditText etTMMobile;
    @BindView(R.id.etTMName)
    AppCompatEditText etTMName;

    List<CityModel> finalCityModels = new ArrayList<>();
    List<DistrictModel> finalDistrictModels = new ArrayList<>();
    List<StateModel> finalStateModels = new ArrayList<>();
    @BindView(R.id.spBloodGroup)
    AppCompatSpinner spBloodGroup;
    String bloodGroup;
    ArrayList<String> bloodlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        tvTitle.setText("Edit Profile");
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

    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void submitFabricator(RegisterModel registerModel) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.EDIT_PROFILE, registerModel, null, this);

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

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.PROFILE, null, null, this);

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
                getProfile();

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


                break;

            case PROFILE:


                registerModel = (RegisterModel) body;
                Prefrences.getInstance().storeUserProfile(EditProfileActivity.this, registerModel.getFirstName(), registerModel.getMobileNo(), "");

                etRetailerId.setText(registerModel.getRetailerId());
                etTMASsingedId.setText(registerModel.getTmAssignedId());
                etTMMobile.setText(registerModel.getTmAssignedMobileNo());
                etTMName.setText(registerModel.getTmAssignedName());
                etTMName.setEnabled(false);

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

            case REGISTER:

                UtilityMethods.showToast(EditProfileActivity.this, "Profile Updated Successfully");
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

            UtilityMethods.showToast(EditProfileActivity.this, "Select Blood Group");

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

            UtilityMethods.showToast(EditProfileActivity.this, "Select State");
            return false;
        } /*else if (TextUtils.isEmpty(city)) {
            UtilityMethods.showToast(EditProfileActivity.this, "Select City");

            return false;
        }*/ else if (TextUtils.isEmpty(district)) {

            UtilityMethods.showToast(EditProfileActivity.this, "Select District");
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

    public void setStateAdapter(List<StateModel> stateModels) {
        spState.setAdapter(new StatesAdapter(EditProfileActivity.this, stateModels));

    }

    public void setCityAdapter(List<CityModel> cityModels) {
        spCity.setAdapter(new CityAdapter(EditProfileActivity.this, cityModels));

    }

    public void setDistrictAdapter(List<DistrictModel> cityModels) {
        spDistrict.setAdapter(new DistrictAdapter(EditProfileActivity.this, cityModels));

    }


    public void setBloodGroupAdapter(List<String> stateModels) {
        spBloodGroup.setAdapter(new CommonAdapter(EditProfileActivity.this, stateModels));

    }

}
