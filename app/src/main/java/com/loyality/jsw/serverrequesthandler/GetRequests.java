package com.loyality.jsw.serverrequesthandler;


import com.loyality.jsw.serverrequesthandler.models.AddProductModel;
import com.loyality.jsw.serverrequesthandler.models.ApproveFabricator;
import com.loyality.jsw.serverrequesthandler.models.CityModel;
import com.loyality.jsw.serverrequesthandler.models.DistrictModel;
import com.loyality.jsw.serverrequesthandler.models.EnquiryModel;
import com.loyality.jsw.serverrequesthandler.models.EventModel;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.ProductModel;
import com.loyality.jsw.serverrequesthandler.models.RegisterModel;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;
import com.loyality.jsw.serverrequesthandler.models.SizeUnitModel;
import com.loyality.jsw.serverrequesthandler.models.StateModel;
import com.loyality.jsw.serverrequesthandler.models.TranscationModel;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public interface GetRequests {

    @GET("profile")
    public Observable<Response<RegisterModel>> getProfile(@Header("Authorization") String auth, @Header("token") String token);


    @GET("profile")
    public Observable<Response<RegisterModel>> getProfileById(@Header("Authorization") String auth, @Header("token") String token);


    @GET("events")
    public Observable<Response<List<EventModel>>> getEvents(@Header("Authorization") String auth, @Header("token") String token);


    @GET("eventDetail")
    public Observable<Response<EventModel>> getEventDetail(@Header("Authorization") String auth, @Header("token") String token,@Query("eventId")String eventID);
    @GET("TM/transactionsUnderRetailer")
    public Observable<Response<List<TranscationModel>>> getFabricatorPurchase(@Header("Authorization") String auth, @Header("token") String token, @Query("retailer_id")String RetailerID, @Query("sort")String sort);

    @GET("transactions")
    public Observable<Response<List<TranscationModel>>> getTransactions(@Header("Authorization") String auth, @Header("token") String token,@Query("sort")String sort);


    @GET("transactionsUnderRetailer")
    public Observable<Response<List<TranscationModel>>> getFabricatorTransaction(@Header("Authorization") String auth, @Header("token") String token,@Query("sort")String sort);

    @GET("transactionDetail")
    public Observable<Response<AddProductModel>> getFabricatorTransactionDetail(@Header("Authorization") String auth, @Header("token") String token,@Query("transactionId")String transactionId);


    @GET("products")
    public Observable<Response<List<ProductModel>>> getAllProducts(@Header("Authorization") String auth, @Header("token") String token);


    @GET("productDetail")
    public Observable<Response<SizeUnitModel>> getProductDetail(@Header("Authorization") String auth, @Header("token") String token, @Query("id")String id);

    @GET("transactionDetail")
    public Observable<Response<TranscationModel>> getTranscationDetail(@Header("Authorization") String auth, @Header("token") String token, @Query("id")String id);


    @GET("productUnits")
    public Observable<Response<List<String>>> getProductUnits(@Header("Authorization") String auth, @Header("token") String token, @Query("id")String id,@Query("size")String size);
    @GET("TM/queries")
    public Observable<Response<List<EnquiryModel>>> getSalesQueries(@Header("Authorization") String auth, @Header("token") String token, @Query("sort")String sort);



    @GET("retailers")
    public Observable<Response<List<RetailerModel>>> getRetailers(@Header("Authorization") String auth, @Header("token") String token,@Query("district")String district);

    @GET("TM/fabricatorUnderRetailer")
    public Observable<Response<List<FabricatorPurchase>>> getAllFabricator(@Header("Authorization") String auth, @Header("token") String token,@Query("retailer_id")String retailer_id);

    @GET("TM/fabricatorProfile")
    public Observable<Response<FabricatorPurchase>> getFabricatorProfile(@Header("Authorization") String auth, @Header("token") String token,@Query("fabricator_id")String fabricator_id,@Query("retailer_id")String retailer_id);

    @GET("states")
    public Observable<Response<List<StateModel>>> getStates(@Header("Authorization") String auth, @Header("token") String token);


    @GET("cities")
    public Observable<Response<List<CityModel>>> getCity(@Header("Authorization") String auth, @Header("token") String token, @Query("name")String id);


    @GET("district")
    public Observable<Response<List<DistrictModel>>> getDistrict(@Header("Authorization") String auth, @Header("token") String token, @Query("name")String id);

    @GET("TM/fabricators")
    public Observable<Response<List<ApproveFabricator>>> getApproveFabricator(@Header("Authorization") String auth, @Header("token") String token, @Query("sort")String sort);


}



