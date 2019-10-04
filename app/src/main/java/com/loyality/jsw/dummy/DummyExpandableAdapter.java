package com.loyality.jsw.dummy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;

import androidx.appcompat.widget.AppCompatTextView;

import com.loyality.jsw.R;

import java.util.List;

public class DummyExpandableAdapter extends BaseExpandableListAdapter {


   DummyExpandableAdapter dummyExpandableAdapter;
   private DummyExpandableListActivity dummyExpandableListActivity;
   private List<HeaderModel> headerModels;
    public DummyExpandableAdapter(DummyExpandableListActivity dummyExpandableListActivity, List<HeaderModel> headerModels) {

        this.dummyExpandableListActivity = dummyExpandableListActivity;
        this.headerModels = headerModels;

    }

    @Override
    public int getGroupCount() {
        return headerModels.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return headerModels.get(groupPosition).getChildDataList().get(headerModels.get(groupPosition).getName()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headerModels.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        List<ChildModel> childModels= headerModels.get(groupPosition).getChildDataList().get(headerModels.get(groupPosition).getName());
        return childModels.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) dummyExpandableListActivity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.dummy_custom_header, null);

        AppCompatTextView appCompatTextView = convertView.findViewById(R.id.tvHeader);


        HeaderModel headerModel = (HeaderModel) getGroup(groupPosition);
        appCompatTextView.setText(headerModel.getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(childPosition==0){

            LayoutInflater layoutInflater = (LayoutInflater) dummyExpandableListActivity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dummy_custom_child_header, null);

            AppCompatTextView appCompatTextView = convertView.findViewById(R.id.tvChildHeader);


            appCompatTextView.setText(headerModels.get(groupPosition).getChildDataList().get(headerModels.get(groupPosition).getName()).get(childPosition).getHeader());


        }
        else{


            LayoutInflater layoutInflater = (LayoutInflater) dummyExpandableListActivity.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.dummy_custom_child, null);

            AppCompatTextView appCompatTextView = convertView.findViewById(R.id.tvChildHeader);


            appCompatTextView.setText(headerModels.get(groupPosition).getChildDataList().get(headerModels.get(groupPosition).getName()).get(childPosition).getName());



        }



     return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
