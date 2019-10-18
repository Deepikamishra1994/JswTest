package com.loyality.jsw.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.loyality.jsw.R;
import com.loyality.jsw.activity.SearchPartnerActivity;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;

import java.util.ArrayList;
import java.util.List;

public class CustomRetailerListAdapter extends ArrayAdapter {

    private List<RetailerModel> dataList;
    public Activity mContext;
    private int itemLayout;

    private ListFilter listFilter = new ListFilter();
    private List<RetailerModel> dataListAllItems;



    public CustomRetailerListAdapter(Activity context, int resource, List<RetailerModel> storeDataLst) {
        super(context, resource, storeDataLst);

        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
    }


    @Override
    public RetailerModel getItem(int position) {


        return dataList.get(position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }



    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(itemLayout, parent, false);
        }

        TextView strName = (TextView) view.findViewById(R.id.tv_retrailerName);
        strName.setText(dataList.get(position).getRetailerName());

        Log.e("testingRetailer",""+dataList.get(position)+" "+dataList.get(position).getRetailerId()+" "+dataList.get(position).getRetailerName());



        return view;
    }

    public RetailerModel getDataItem(int postion){

        Log.e("size",""+dataList.size());
        return dataList.get(postion);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<RetailerModel>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<RetailerModel> matchValues = new ArrayList<RetailerModel>();

                for (RetailerModel dataItem : dataListAllItems) {
                    if (dataItem.getRetailerName().toLowerCase().startsWith(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<RetailerModel>)results.values;
                ((SearchPartnerActivity) mContext).getRetailerData(dataList);
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                Toast.makeText(mContext,"No Retailer for this state",Toast.LENGTH_LONG).show();
                notifyDataSetInvalidated();
            }
        }

    }
}
