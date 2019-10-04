package com.loyality.jsw.serverrequesthandler.upload;



import com.loyality.jsw.Constants.ApplicationConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hocrox_java on 29/01/16.
 */
public class ServiceGenerator {

    public static final String UPLOAD_MEDIA_URL = ApplicationConstants.baseApi;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    static OkHttpClient okHttpClient = httpClient.connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS).build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(UPLOAD_MEDIA_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }
}