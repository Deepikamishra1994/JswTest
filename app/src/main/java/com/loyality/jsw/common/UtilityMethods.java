package com.loyality.jsw.common;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.loyality.jsw.R;


public class UtilityMethods {

    static Dialog dialog_changed;
    static UtilityMethods utilityMethods;
    static ProgressDialog progressDialog;
    static Context contexts;

    public static boolean isNetworkAvailable(Context context) {
        try {


            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void dismissDialog() {
        if (dialog_changed != null) {
            dialog_changed.dismiss();
        }
    }

    public static UtilityMethods getInstance() {

        return utilityMethods;
    }


    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
/*

    public static void showDialog(Context c) {
        if (dialog_changed != null) {
            dialog_changed.dismiss();
        }
        dialog_changed = new Dialog(c);
        dialog_changed.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_changed.setContentView(R.layout.progressdialog);
        dialog_changed.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog_changed.setCancelable(false);
        dialog_changed.show();
    }
*/


    private static Toast mToast = null;


    public static void showToast(Context context, String message) {

        if(mToast!=null){

            mToast.cancel();
        }
        mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        mToast.show();



    }



    public static void showProgressDialog(Context context, String title, String message) {


       /* contexts = context;
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressDialog = new ProgressDialog(context);
            } else {
                progressDialog = new ProgressDialog(context, R.style.ProgressDialogStyle);
            }
            progressDialog.setTitle(title);
            progressDialog.setCancelable(false);
            progressDialog.setSuccess(success);
            progressDialog.setCancelable(false);
            progressDialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }*/

        contexts = context;
        try {
          /*  if (progressDialog != null) {
                progressDialog.dismiss();
            }*/
           /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressDialog = new ProgressDialog(context);
            } else {
           */


            if (dialog_changed != null) {
                dialog_changed.dismiss();
            }
            dialog_changed = new Dialog(context);
            dialog_changed.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog_changed.setContentView(R.layout.custom_progress);
            dialog_changed.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog_changed.setCancelable(false);
            dialog_changed.show();


     /*       progressDialog = new ProgressDialog(context, R.style.ProgressDialogStyle);
            //}
            progressDialog.setTitle(title);
            progressDialog.setCancelable(false);
            progressDialog.setSuccess(success);
            progressDialog.setCancelable(false);
            progressDialog.show();*/


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void dismissProgressDialog() {
      /*  try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            Log.e("progress problem", "yes");
        }
*/

        try {
            /*if (progressDialog != null) {
                progressDialog.dismiss();
            }*/

            if (dialog_changed != null) {

                dialog_changed.dismiss();
            }

        } catch (Exception e) {
            Log.e("progress problem", "yes");
        }
    }


    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            // showDialogGPS(context);
        }
        return enabled;
    }

    private static void showDialogGPS(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setMessage("Do you want to enable your GPS?");
        builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(
                        new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static void sendAlertDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        //  builder.setTitle("Enable GPS");
        builder.setMessage("Need Blood Today or Later?");
        builder.setPositiveButton("Today", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static void shareApp(String appUrl, Context context) {
        try {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Download App");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, appUrl);
            context.startActivity(Intent.createChooser(sharingIntent, "Share RedLifeLine app via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
