package com.loyality.jsw.serverrequesthandler;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loyality.jsw.common.UtilityMethods;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class PostRequestCallback<T> {


    PostDispatchs postDispatchs;
    Response responses;
    ResponseTypes responseTypes;
    Context context;

    public PostRequestCallback(PostDispatchs postDispatch, Response response, ResponseTypes responseTypes, Context context) {

        postDispatchs = postDispatch;
        responses = response;
        this.responseTypes = responseTypes;
        this.context = context;

    }


    public void onResponse() {

        UtilityMethods.dismissProgressDialog();

        if (responses.isSuccessful()) {

            Gson gson = new Gson();
            Log.e("server Response", "" + gson.toJson(responses.body()));

            postDispatchs.apiSuccess(responses.body(), responseTypes);

        } else {

            Gson gson = new GsonBuilder().create();


            ErrorDto errorDTO = null;
            try {
                errorDTO = gson.fromJson(responses.errorBody().string(), ErrorDto.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (context != null) {

                if (errorDTO != null) {



                    UtilityMethods.showToast(context, errorDTO.getError());

                    postDispatchs.apiError(errorDTO);

                }


            }

        }

    }



}