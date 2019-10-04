package com.loyality.jsw.common;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class Prefrences {


    public String USER_DETAIL = "user_detail";
    public String SYNC_DETAIL = "sync_detail";
    public String DATA_ENTRY = "data_entry";

    public String USER_PROFILE = "user_profile";


    public String PARTNER_NAME = "partnerName";
    public String PARTNER_MOBILE = "partnerMobile";
    public String PARTNER_IMAGE = "partnerImage";


    public String PARTNER_CODE = "partnerCode";
    public String PARTNER_TYPE = "partner_type";
    public String SYNC_DATE = "sync_date";
    public String APP_LANG = "app_lang";



    public String USER_TOKEN = "user_token";


    public String IS_SR_ENTER_DATA = "sr_add_data";

    public String CURRENT_DATE = "current_date";

    public static Prefrences getInstance() {
        return new Prefrences();
    }


    public void storeUserData(Context context, String userid, String type, String token) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PARTNER_CODE, userid);
        editor.putString(PARTNER_TYPE, type);
        editor.putString(USER_TOKEN, "Bearer "+token);
        editor.apply();

    }
     public void storeLanguage(Context context,String langauge){


         SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putString(APP_LANG, langauge);
         editor.apply();

     }


    public String getLanguage(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(APP_LANG, "en");
    }


    public void storeUserProfile(Context context, String name, String mobile, String image) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PARTNER_NAME, name);
        editor.putString(PARTNER_MOBILE, mobile);
        editor.putString(PARTNER_IMAGE,image);
        editor.apply();

    }

    public void storeDataEntry(Context context, boolean sr_add_data, String currentDate) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_ENTRY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_SR_ENTER_DATA, sr_add_data);
        editor.putString(CURRENT_DATE, currentDate);
        editor.apply();
    }


    public boolean getDataEntry(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_ENTRY, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(IS_SR_ENTER_DATA, false);
    }


    public String getCurrentDataEntryDate(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA_ENTRY, Context.MODE_PRIVATE);

        return sharedPreferences.getString(CURRENT_DATE, "");
    }


    public void storeSyncDetail(Context context, String date) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SYNC_DATE, date);

        editor.apply();
    }


    public String getSyncDate(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(SYNC_DATE, null);
    }


    public String getToken(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(USER_TOKEN, "");

    }

    public String getPartnerCode(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_CODE, "");

    }


    public String getPartnerImage(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_IMAGE, "");

    }

    public String getPartnerName(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_NAME, "");

    }



    public String getPartnerMobile(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_MOBILE, "");

    }


    public String getPartnerUrl(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_PROFILE, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_IMAGE, "");

    }

    public String getPartnerType(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);

        return sharedPreferences.getString(PARTNER_TYPE, "");

    }

    public void clearUserDetail(Context context) {


        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PARTNER_CODE, "");
        editor.putString(USER_TOKEN,"");
        editor.clear();
        editor.apply();


/*
        SharedPreferences sharedPreferences1 = context.getSharedPreferences(SYNC_DETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.clear();
        editor1.apply();
*/

    }


}
