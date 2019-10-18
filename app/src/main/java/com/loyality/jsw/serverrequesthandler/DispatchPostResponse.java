package com.loyality.jsw.serverrequesthandler;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.loyality.jsw.Constants.ApplicationConstants;
import com.loyality.jsw.common.Prefrences;
import com.loyality.jsw.common.UtilityMethods;
import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.LoginModel;
import com.loyality.jsw.serverrequesthandler.models.LoginResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;


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

public class DispatchPostResponse {


    public static void disptatchRequest(final PostDispatchs postDispatchs, final ResponseTypes type, Object object, Object o, final Context context) {


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(builder).baseUrl(ApplicationConstants.baseApi).build();
        PostRequests postRequests = retrofit.create(PostRequests.class);


        switch (type) {



            case LOGIN:

                postRequests.login(ApplicationConstants.auth_token,(LoginModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<LoginResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<LoginResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.LOGIN, context).onResponse();

                    }
                });



                break;

            case ADD_RETAILER:
                postRequests.addRetailer(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(RetailerModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.ADD_RETAILER, context).onResponse();

                    }
                });

                break;
            case SUBMITTM_PROFILE:
                postRequests.addTmProfile(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(RegisterModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.ADD_RETAILER, context).onResponse();

                    }
                });

                break;


            case APPROVE_FABRICATOR:
                Gson gson = new Gson();
                String data = gson.toJson(object);
                Log.e("data",data);
                postRequests.approveFabricator(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(RegisterModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.APPROVE_FABRICATOR, context).onResponse();

                    }
                });

                break;
            case QUERYRESULT:

                postRequests.queryResult(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(EnquiryModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<EnquiryModel>>() {

                    @Override
                    public void onCompleted() {

                    }


                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<EnquiryModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.QUERYRESULT, context).onResponse();

                    }
                });

                break;
            case UPDATE_PURCHASE_VERIFICATION:
                postRequests.fabricatorPurchaseVerify(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(AddProductModel) object,String.valueOf(o)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<FabricatorPurchase>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<FabricatorPurchase> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.UPDATE_PURCHASE_VERIFICATION, context).onResponse();

                    }
                });

                break;


            case FORGOT_PASSWORD:
                postRequests.forgotPassword(ApplicationConstants.auth_token,String.valueOf(object)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.FORGOT_PASSWORD, context).onResponse();

                    }
                });

                break;

            case ENQUIRY:

                postRequests.submitQuery(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(EnquiryModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.ENQUIRY, context).onResponse();

                    }
                });



                break;


            case REGISTER:


                postRequests.register(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(RegisterModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.REGISTER, context).onResponse();

                    }
                });

                break;
            case EDIT_PROFILE:

                Gson gson1 = new Gson();
                String data1 = gson1.toJson(object);
                Log.e("data",data1);
                postRequests.editProfile(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(RegisterModel) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.EDIT_PROFILE, context).onResponse();

                    }
                });

                break;


            case ADD_PRODUCTS:


                postRequests.addProduct(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(List<AddProductModel>) object).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.ADD_PRODUCTS, context).onResponse();

                    }
                });

                break;

            case ADD_TRANSACTION_STATUS:


                postRequests.addTransactionStatus(ApplicationConstants.auth_token,Prefrences.getInstance().getToken(context),(AddProductModel) object,String.valueOf(o)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<ResponseModel>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("tesitng exception", "" + e.getMessage());

                        if (e.getMessage() != null && !TextUtils.isEmpty(e.getMessage())) {

                            submitNotify(context,type+" "+e.getCause()+" "+e.getMessage());

                            UtilityMethods.showToast(context, e.getMessage());

                        }
                        UtilityMethods.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(Response<ResponseModel> listResponse) {

                        new PostRequestCallback<>(postDispatchs, listResponse, ResponseTypes.ADD_TRANSACTION_STATUS, context).onResponse();

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
