package com.loyality.jsw.serverrequesthandler;


import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.LoginModel;
import com.loyality.jsw.serverrequesthandler.models.LoginResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.ResponseModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public interface PostRequests {

/*

    @POST("login")
    public Observable<Response<LoginResponseModel>> login(@Header("Authorization") String authorize, @Body LoginResponseModel LoginResponseModel);


    @POST("registerDevice")
    public Call<ResponseModel> registerDevice(@Header("Authorization") String authorize, @Header("token") String token, @Header("deviceToken") String deviceToken);
*/


    @POST("register")
    public Observable<Response<ResponseModel>> register(@Header("Authorization") String authorize,@Header("token") String token , @Body RegisterModel registerModel);


    @POST("editProfile")
    public Observable<Response<ResponseModel>> editProfile(@Header("Authorization") String authorize,@Header("token") String token , @Body RegisterModel registerModel);


    @POST("login")
    public Observable<Response<LoginResponseModel>> login(@Header("Authorization") String authorize, @Body LoginModel loginModel);


    @POST("query")
    public Observable<Response<ResponseModel>> submitQuery(@Header("Authorization") String authorize,@Header("token") String token ,@Body EnquiryModel enquiryModel);


    @POST("addProduct")
    public Observable<Response<ResponseModel>> addProduct(@Header("Authorization") String authorize,@Header("token") String token , @Body List<AddProductModel> addProductModels);

    @POST("transactionApproval")
    public Observable<Response<ResponseModel>> addTransactionStatus(@Header("Authorization") String authorize,@Header("token") String token,@Body AddProductModel addProductModels,@Query("status") String transactionStatus );



    @POST("addRetailer")
    public Observable<Response<ResponseModel>> addRetailer(@Header("Authorization") String authorize,@Header("token") String token , @Body RetailerModel retailerModel);
    @POST("addRetailer")
    public Observable<Response<ResponseModel>> addTmProfile(@Header("Authorization") String authorize,@Header("token") String token , @Body RegisterModel retailerModel);



    @POST("TM/fabricatorApproval")
    public Observable<Response<ResponseModel>> approveFabricator(@Header("Authorization") String authorize,@Header("token") String token , @Body RegisterModel registerModel);




    @POST("TM/transactionApproval")
    public Observable<Response<FabricatorPurchase>> fabricatorPurchaseVerify(@Header("Authorization") String authorize, @Header("token") String token , @Body AddProductModel registerModel, @Query("status") String status);
    @POST("TM/queryResult ")
    public Observable<Response<EnquiryModel>> queryResult(@Header("Authorization") String authorize, @Header("token") String token , @Body EnquiryModel registerModel);



    @POST("forgotPassword")
    public Observable<Response<ResponseModel>> forgotPassword(@Header("Authorization") String authorize, @Query("userId") String  id);


}

