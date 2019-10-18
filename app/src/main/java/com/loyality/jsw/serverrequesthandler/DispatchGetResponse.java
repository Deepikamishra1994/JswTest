package com.loyality.jsw.serverrequesthandler;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.loyality.jsw.Constants.ApplicationConstants;
import com.loyality.jsw.common.Prefrences;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.ApproveFabricator;
import com.loyality.jsw.serverrequesthandler.models.BillingModel;
import com.loyality.jsw.serverrequesthandler.models.CityModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.EventModel;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.ProductModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;
import com.loyality.jsw.serverrequesthandler.models.SizeUnitModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class DispatchGetResponse {


    public static void disptatchRequest(final GetDispatchs getDispatch, final ResponseTypes type, Object object, Object object2, final Context context) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).baseUrl(ApplicationConstants.baseApi).client(builder).build();
        GetRequests getRequest = retrofit.create(GetRequests.class);


        switch (type) {


            case PROFILE:
                getRequest.getProfile(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<RegisterModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<RegisterModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.PROFILE, context).onResponse();

                    }
                });


                break;

            case PROFILE_BY_ID:
                getRequest.getProfileById(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<RegisterModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<RegisterModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.PROFILE_BY_ID, context).onResponse();

                    }
                });


                break;


            case EVENTS:

                getRequest.getEvents(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<EventModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<EventModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.EVENTS, context).onResponse();

                    }
                });


                break;

            case EVENT_DETAIL:

                getRequest.getEventDetail(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), "" + object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<EventModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<EventModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.EVENT_DETAIL, context).onResponse();

                    }
                });


                break;
            case FABRICATOR_PURCHASE:

                getRequest.getFabricatorPurchase(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), "" + object, String.valueOf(object2)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<TranscationModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<TranscationModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.FABRICATOR_PURCHASE, context).onResponse();

                    }
                });


                break;

            case RETAILER_TRANSACTIONS:

                getRequest.getFabricatorTransaction(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),  String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<TranscationModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<TranscationModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.RETAILER_TRANSACTIONS, context).onResponse();

                    }
                });


                break;



            case RETAILER_TRANSACTIONS_DETAIL:

                getRequest.getFabricatorTransactionDetail(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AddProductModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<AddProductModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.RETAILER_TRANSACTIONS_DETAIL, context).onResponse();

                    }
                });


                break;

            case TRANSACTIONS:

                getRequest.getTransactions(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<TranscationModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<TranscationModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.TRANSACTIONS, context).onResponse();

                    }
                });


                break;

            case ALL_PRODUCTS:

                getRequest.getAllProducts(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<ProductModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<ProductModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.ALL_PRODUCTS, context).onResponse();

                    }
                });


                break;


            case PRODUCT_DETAIL:

                getRequest.getProductDetail(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<SizeUnitModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<SizeUnitModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.PRODUCT_DETAIL, context).onResponse();

                    }
                });


                break;

            case TRANSCATION_DETAIL:


                getRequest.getProductDetail(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context), String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<SizeUnitModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<SizeUnitModel> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.PRODUCT_DETAIL, context).onResponse();

                    }
                });



            break;

            case PRODUCT_UNITS:

                getRequest.getProductUnits(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),String.valueOf(object),String.valueOf(object2)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<String>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<String>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.PRODUCT_UNITS, context).onResponse();

                    }
                });



                break;
            case SALES_QUERY:

                getRequest.getSalesQueries(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<EnquiryModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<EnquiryModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.SALES_QUERY, context).onResponse();

                    }
                });



                break;


            case APPROVE_FABRICATOR:

                getRequest.getApproveFabricator(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<ApproveFabricator>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<ApproveFabricator>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.APPROVE_FABRICATOR, context).onResponse();

                    }
                });



                break;
            case RETAILERS:

                getRequest.getRetailers(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),""+object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<RetailerModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<RetailerModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.RETAILERS, context).onResponse();

                    }
                });


                break;
            case SEARCH_FABRICATOR:

                getRequest.getAllFabricator(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),""+object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<FabricatorPurchase>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<FabricatorPurchase>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.SEARCH_FABRICATOR, context).onResponse();

                    }
                });
            case FABRICATOR_PROFILE:

                getRequest.getFabricatorProfile(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),""+object,""+object2).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<FabricatorPurchase>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<FabricatorPurchase> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.SEARCH_FABRICATOR, context).onResponse();

                    }
                });


                break;
            case STATES:

                getRequest.getStates(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<StateModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<StateModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.STATES, context).onResponse();

                    }
                });


                break;
            case CITIES:

                getRequest.getCity(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<CityModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<CityModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.CITIES, context).onResponse();

                    }
                });


                break;

            case DISTRICT:

                getRequest.getDistrict(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<List<DistrictModel>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {


                            UtilityMethods.showToast(context, e.getMessage());


                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<List<DistrictModel>> listResponse) {

                        new GetRequestCallback<>(getDispatch, listResponse, ResponseTypes.DISTRICT, context).onResponse();

                    }
                });


                break;




        }


    }
    private static void submitNotify(Context context,String data) {
        // TODO: Implement this method to send token to your app server.


        if(context!=null){

/*
            if (!TextUtils.isEmpty(Prefrences.getInstance().getToken(context))) {

                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient builder = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build();

                Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(builder).baseUrl(ApplicationConstants.baseApi).build();
                PostRequests postRequests = retrofit.create(PostRequests.class);

                ResponseModel responseModel = new ResponseModel();
                responseModel.setError(data);
                Call<ResponseModel> response = postRequests.submitNotify(ApplicationConstants.auth_token, Prefrences.getInstance().getToken(context),responseModel);
                response.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {



                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {


                        //    UtilityMethods.dismissProgressDialog();
                    }
                });

            }
*/


        }


    }

}
