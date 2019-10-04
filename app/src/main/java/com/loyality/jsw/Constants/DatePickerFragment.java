package com.loyality.jsw.Constants;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

   private View view;

   DatePickerDialog datePickerDialog;
   boolean isDob = false;
    public DatePickerFragment(View v) {

        view = v;
    }

    public DatePickerFragment(View v,boolean value) {

        view = v;
        isDob = value;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it


        datePickerDialog =  new DatePickerDialog(getActivity(), this, year, month, day);
      if(isDob){
          setDobDate();
      }else{
          setMaxDate();

      }

         return datePickerDialog;
    }

    public void setMaxDate(){

        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void setDobDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -14);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
    }


    @Override
    public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {

        String date = year+"-"+(month+1)+"-"+dayOfMonth;

        ((AppCompatEditText)view).setText(date);

    }

}