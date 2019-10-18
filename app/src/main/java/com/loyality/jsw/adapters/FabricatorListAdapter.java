package com.loyality.jsw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.loyality.jsw.R;
import com.loyality.jsw.serverrequesthandler.models.FabricatorPurchase;
import com.loyality.jsw.serverrequesthandler.models.RetailerModel;

import java.util.ArrayList;
import java.util.List;

public class FabricatorListAdapter extends ArrayAdapter {

    private List<FabricatorPurchase> dataList;
    private Context mContext;
    private int itemLayout;

    private FabricatorListAdapter.ListFilter listFilter = new FabricatorListAdapter.ListFilter();
    private List<FabricatorPurchase> dataListAllItems;


    public FabricatorListAdapter(Context context, int resource, List<FabricatorPurchase> storeDataLst) {
        super(context, resource, storeDataLst);
        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
    }


    @Override
    public FabricatorPurchase getItem(int position) {
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
        strName.setText(dataList.get(position).getFirstName());
        return view;
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
                    dataListAllItems = new ArrayList<FabricatorPurchase>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<FabricatorPurchase> matchValues = new ArrayList<FabricatorPurchase>();

                for (FabricatorPurchase dataItem : dataListAllItems) {
                    if (dataItem.getPartnerCode().toLowerCase().startsWith(searchStrLowerCase)) {
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
                dataList = (ArrayList<FabricatorPurchase>) results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                Toast.makeText(mContext, "No Fabricator ", Toast.LENGTH_LONG).show();
                notifyDataSetInvalidated();
            }
        }

    }
}
