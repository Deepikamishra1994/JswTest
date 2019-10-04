package com.loyality.jsw;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loyality.jsw.Constants.DatePickerFragment;
import com.loyality.jsw.adapters.AddProductAdapter;
import com.loyality.jsw.adapters.EqualSpacingItemDecoration;
import com.loyality.jsw.adapters.ProductAdapter;
import com.loyality.jsw.adapters.RetailersAdapter;
import com.loyality.jsw.adapters.SizeUnitsAdapter;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.DispatchGetResponse;
import com.loyality.jsw.serverrequesthandler.DispatchPostResponse;
import com.loyality.jsw.serverrequesthandler.ErrorDto;
import com.loyality.jsw.serverrequesthandler.GetDispatchs;
import com.loyality.jsw.serverrequesthandler.PostDispatchs;
import com.loyality.jsw.serverrequesthandler.ResponseTypes;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.ProductModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;
import com.loyality.jsw.serverrequesthandler.models.SizeUnitModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesDetailActivity extends AppCompatActivity implements GetDispatchs, PostDispatchs {


    String productName, size, retailer, price, unit, sheets;
    List<AddProductModel> addProductModelList = new ArrayList<>();
    @BindView(R.id.ivBack)
    AppCompatImageButton ivBack;
    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;
    @BindView(R.id.etDatePurchase)
    AppCompatEditText etDatePurchase;
    @BindView(R.id.spProductName)
    AppCompatSpinner spProductName;
    @BindView(R.id.spSizes)
    AppCompatSpinner spSizes;
    @BindView(R.id.spUnits)
    AppCompatSpinner spUnits;
    @BindView(R.id.etQunatity)
    AppCompatEditText etQunatity;
    @BindView(R.id.etAmount)
    AppCompatEditText etAmount;
    @BindView(R.id.spRetailer)
    AppCompatSpinner spRetailer;
    @BindView(R.id.btnAddProduct)
    AppCompatButton btnAddProduct;
    @BindView(R.id.rlProduct)
    RecyclerView rlProduct;
    @BindView(R.id.btnSubmit)
    AppCompatButton btnSubmit;
    @BindView(R.id.etSheets)
    AppCompatEditText etSheets;
    @BindView(R.id.tvAddRetailer)
    AppCompatTextView tvAddRetailer;
    @BindView(R.id.tvSheetTitle)
    AppCompatTextView tvSheetTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        tvTitle.setText("Add Purchase");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addProudctValidate()) {

                    UtilityMethods.showToast(SalesDetailActivity.this, "Product Added Successfully");
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (addProductModelList.size() > 0) {

                    addProduct(addProductModelList);
                } else {

                    UtilityMethods.showToast(SalesDetailActivity.this, "Add Product");

                }
            }
        });

        etDatePurchase.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    showDatePickerDialog(v);
                }


            }
        });
        etDatePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });


        spProductName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position > 0) {

                    ProductModel productModel = (ProductModel) spProductName.getAdapter().getItem(position);

                    productName = productModel.getProductName();


                    getProductDetail(productModel.getProductName());

                } else {

                    spSizes.setAdapter(null);
                    spUnits.setAdapter(null);
                    etQunatity.setText("");
                    etAmount.setText("");
                    productName = "";
                    size = "";
                    unit = "";
                    price = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                unit = (String) spUnits.getAdapter().getItem(position);


                if (!TextUtils.isEmpty(unit)) {

                    if (unit.equalsIgnoreCase("r.f") || unit.equalsIgnoreCase("r.m")) {

                        etSheets.setVisibility(View.VISIBLE);
                        tvSheetTitle.setVisibility(View.VISIBLE);

                    } else {

                        etSheets.setVisibility(View.GONE);
                        tvSheetTitle.setVisibility(View.GONE);

                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spSizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                size = (String) spSizes.getAdapter().getItem(position);
                getProductUnits(productName,size);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvAddRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(SalesDetailActivity.this, AddNewRetailerActivity.class), 1000);

            }
        });

        spRetailer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                RetailerModel retailerModel = (RetailerModel) spRetailer.getAdapter().getItem(position);

                retailer = retailerModel.getRetailerId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setAdapter();
        getAllProduct();

        //  getAllRetailers();

    }

    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public boolean addProudctValidate() {

        String dateOfProduct = String.valueOf(etDatePurchase.getText());
        String qty = String.valueOf(etQunatity.getText());
        String amount = String.valueOf(etAmount.getText());
        sheets = String.valueOf(etSheets.getText());
        if (TextUtils.isEmpty(dateOfProduct)) {

            etDatePurchase.setError("Enter Date of Product");
            return false;
        } else if (TextUtils.isEmpty(qty)) {

            etQunatity.setError("Enter Quantity");
            return false;

        } else if (TextUtils.isEmpty(amount)) {

            etAmount.setError("Enter Amount");
            return false;

        } else if (TextUtils.isEmpty(productName)) {

            UtilityMethods.showToast(SalesDetailActivity.this, "Select Product");
            return false;

        } else if (TextUtils.isEmpty(unit)) {

            UtilityMethods.showToast(SalesDetailActivity.this, "Select Unit");
            return false;

        } else if (TextUtils.isEmpty(retailer)) {

            UtilityMethods.showToast(SalesDetailActivity.this, "Select Retailer");
            return false;

        }

        if (unit.equalsIgnoreCase("r.f") || unit.equalsIgnoreCase("r.m")) {

            if (TextUtils.isEmpty(sheets)) {

                etSheets.setError("Enter Sheets");
                return false;

            }

        }


        AddProductModel addProductModel = new AddProductModel();
        addProductModel.setAmount(amount);
        addProductModel.setDateOfProduct(dateOfProduct);
        addProductModel.setProductName(productName);
        addProductModel.setQuantity(qty);
        addProductModel.setRetailer(retailer);
        addProductModel.setSize(size);
        addProductModel.setUnit(unit);
        addProductModel.setSheets(sheets);

        addProductModelList.add(addProductModel);

        if (rlProduct.getAdapter() != null) {

            rlProduct.getAdapter().notifyDataSetChanged();
        }

        return true;
    }


    public void setAdapter() {

        AddProductAdapter addProductAdapter = new AddProductAdapter(SalesDetailActivity.this, addProductModelList);
        rlProduct.setLayoutManager(new LinearLayoutManager(this));
        rlProduct.addItemDecoration(new EqualSpacingItemDecoration(18));
        rlProduct.setAdapter(addProductAdapter);

    }

    public void setRetailerAdapter(List<RetailerModel> retailerModels) {
        spRetailer.setAdapter(new RetailersAdapter(SalesDetailActivity.this, retailerModels));

    }


    public void productNameAdapter(List<ProductModel> productModels) {

        spProductName.setAdapter(new ProductAdapter(SalesDetailActivity.this, productModels));

    }

    public void productUnitsAdapter(List<String> unitsModels) {

        spUnits.setAdapter(new SizeUnitsAdapter(SalesDetailActivity.this, unitsModels));

    }

    public void productSizesAdapter(List<String> sizesModels) {

        spSizes.setAdapter(new SizeUnitsAdapter(SalesDetailActivity.this, sizesModels));

    }


    @Override
    public void apiSuccess(Object body, ResponseTypes response) {

        switch (response) {

            case ADD_PRODUCTS:

                UtilityMethods.showToast(SalesDetailActivity.this, "Product has been added successfully");
                onBackPressed();
                break;

            case ALL_PRODUCTS:

                List<ProductModel> productModels = (List<ProductModel>) body;
                if (productModels != null && productModels.size() > 0) {


                    List<ProductModel> finalProductModels = new ArrayList<>();
                    ProductModel productModel = new ProductModel();
                    productModel.setProductName("Select Product");
                    finalProductModels.add(productModel);
                    finalProductModels.addAll(productModels);

                    productNameAdapter(finalProductModels);
                }

                getAllRetailers();

                break;

            case RETAILERS:

                List<RetailerModel> retailerModels = (List<RetailerModel>) body;
                if (retailerModels != null && retailerModels.size() > 0) {


                    setRetailerAdapter(retailerModels);
                }


                break;

            case PRODUCT_DETAIL:

                SizeUnitModel sizeUnitModels = (SizeUnitModel) body;
                if (sizeUnitModels != null) {


                    if (sizeUnitModels.getSizes() != null && sizeUnitModels.getSizes().size() > 0) {

                        productSizesAdapter(sizeUnitModels.getSizes());
                    }


                }
                break;
            case PRODUCT_UNITS:


                List<String> units = (List<String>) body;
                if (units != null && units.size() > 0) {


                        productUnitsAdapter(units);


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

    public void addProduct(List<AddProductModel> addProductModels) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchPostResponse.disptatchRequest(this, ResponseTypes.ADD_PRODUCTS, addProductModels, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getTranscationDetail(String transcationId) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.TRANSCATION_DETAIL, transcationId, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    public void getAllProduct() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.ALL_PRODUCTS, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getProductDetail(String id) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.PRODUCT_DETAIL, id, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getProductUnits(String id,String size) {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.PRODUCT_UNITS, id, size, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }

    public void getAllRetailers() {

        if (UtilityMethods.isNetworkAvailable(this)) {

            UtilityMethods.showProgressDialog(this, null, "Please Wait...");

            DispatchGetResponse.disptatchRequest(this, ResponseTypes.RETAILERS, null, null, this);

        } else {
            UtilityMethods.showToast(this, getResources().getString(R.string.no_internet));
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getAllRetailers();
    }
}

