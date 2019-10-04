package com.loyality.jsw.Constants;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;

/**
 * Created by Sahil Goyal on 3/8/2018.
 */

public class ApplicationConstants {

    //SRDEL@91
    //tata
   // public static final String baseApi = "https://www.ebandhuvat.in/partnerAPI/";



  //  public static final String baseApi = "http://40.122.40.10/jsw_demo/";

   public static final String baseApi = "http://40.122.40.10/jsw_demo/";
//    public static final String baseApi = "http://52.158.208.73/jsw_loyalty/";


    public static final String imageBaseUrl="http://40.122.40.10/jsw_demo/";
  public static final String rewardBaseUrl="http://40.122.40.10/jsw_demo/";
  public static final String largeimageBaseUrl="https://gcl.channelplay.in/uploads/Reward/imageLarge/";
  public static final String bannerImageUrl="https://gcl.channelplay.in/uploads/BannerImage/";




/*
    public static final String baseApi = "https://dev-gcl.channelplay.in/mechanic/";
    public static final String imageBaseUrl="https://dev-gcl.channelplay.in/uploads/image/";
    public static final String rewardBaseUrl="https://dev-gcl.channelplay.in/uploads/Reward/";
    public static final String largeimageBaseUrl="https://dev-gcl.channelplay.in/uploads/Reward/imageLarge/";
    public static final String bannerImageUrl="https://dev-gcl.channelplay.in/uploads/BannerImage/";
*/







    /*public static final String baseApi = "http://35.192.239.123/greaves_cotton/mechanic/";
     public static final String imageBaseUrl="http://35.192.239.123/greaves_cotton/uploads/image/";
   public static final String largeimageBaseUrl="http://35.192.239.123/greaves_cotton/uploads/Reward/imageLarge/";

    public static final String rewardBaseUrl="http:/35.192.239.123/greaves_cotton/uploads/Reward/";
   */  public static final String auth_token = "Basic YWRtaW46MTIzNDU2Nw==";
   // public static final String imageBaseUrl = "https://www.ebandhuvat.in/";

    public static final double file_size = 3;
    public static final double file_size_5MB = 5;
    public static boolean isIntiativeAdded = false;


// public static final String baseApi="http://datasofttechs.com/webservices/";


    public static void dismissKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    public static String encodeImage(Uri uri) {


        Bitmap bm = BitmapFactory.decodeFile(uri.getPath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    public static String dummyEncodeFile(Bitmap bm) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("testingEncodedString", "" + encodedImage);

        String outputString = "";
        try {
            // Encode a String into bytes
            byte[] input = encodedImage.getBytes("UTF-8");

            // Compress the bytes
            byte[] output = new byte[encodedImage.length()];
            Deflater compresser = new Deflater();
            compresser.setInput(input);
            compresser.finish();
            int compressedDataLength = compresser.deflate(output);
            compresser.end();

            // Decompress the bytes
         /*   Inflater decompresser = new Inflater();
            decompresser.setInput(output, 0, compressedDataLength);
            byte[] result = new byte[100];
            int resultLength = decompresser.inflate(result);
            decompresser.end();*/

            // Decode the bytes into a String
            outputString = new String(output, 0, compressedDataLength, "UTF-8");
        } catch (java.io.UnsupportedEncodingException ex) {
            // handle
        }
        Log.e("testingOutputString", "" + outputString);

        return outputString;
    }

    public static double getFileSize(String path) {

        double megabytes = 0;
        try {

            if (!path.isEmpty() && !TextUtils.isEmpty(path)) {

                File file = new File(Uri.parse(path).getPath());

                double bytes = file.length();
                double kilobytes = (bytes / 1024);
                megabytes = (kilobytes / 1024);
                Log.e("filesize", "" + megabytes + " " + path + " " + file.length());


            }
        } catch (Exception e) {

            Log.e("File Detect Exception", "" + megabytes);
        }


        return megabytes;
    }


    public static double getDummyFileSize(File file) {

        double megabytes = 0;
        try {

            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            megabytes = (kilobytes / 1024);
            Log.e("filesize", "" + megabytes + " " + " " + file.length());

        } catch (Exception e) {

            Log.e("File Detect Exception", "" + megabytes);
        }


        return megabytes;
    }


    public static long convertTomills(String time) {

        long timeInMilliseconds = 0;

        if (time != null || !TextUtils.isEmpty(time)) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date mDate = sdf.parse(time);
                timeInMilliseconds = mDate.getTime();
                System.out.println("Date in milli :: " + timeInMilliseconds);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        return timeInMilliseconds;
    }

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    public static String getTimeAgo(long time, Context ctx) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        Log.e("testing", "" + diff + " " + now + "  " + time);
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a min ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " mins ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "a hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hrs ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
