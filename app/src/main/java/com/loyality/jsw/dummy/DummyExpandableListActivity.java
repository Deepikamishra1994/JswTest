package com.loyality.jsw.dummy;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.loyality.jsw.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DummyExpandableListActivity extends AppCompatActivity {

    @BindView(R.id.elList)
    ExpandableListView elList;



    List<HeaderModel> headerModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_expandable_list);
        ButterKnife.bind(this);


        prepareData();

    }



    public void prepareData(){
      /*  List<HeaderModel> headerModels = new ArrayList<>();

        HeaderModel headerModel = new HeaderModel();
        headerModel.setCount("2");
        headerModel.setId("1");
        headerModel.setName("Lights");




        HashMap<String,List<ChildModel>> childDataList = headerModel.getChildDataList();

        ChildModel childModel = new ChildModel();
        String header = childModel.getHeader();

        if(childDataList.containsKey(header)){

            List<ChildModel> arrayList =  headerModel.getChildDataList().get(header);
            ChildModel childModel1 = new ChildModel();




        }
*/

        List<ChildModel> childModels = new ArrayList<>();

        ChildModel childModel= new ChildModel();
        childModel.setChecked(true);
        childModel.setCount("100");
        childModel.setHeader("Bedroom");
        childModel.setId("1");
        childModel.setLightOn(true);
        childModel.setName("Reading Light");
        childModels.add(childModel);


        ChildModel childModel1= new ChildModel();
        childModel1.setChecked(true);
        childModel1.setCount("100");
        childModel1.setHeader("Bedroom");
        childModel1.setId("1");
        childModel1.setLightOn(true);
        childModel1.setName("Reading Light 1");
        childModels.add(childModel1);






        ChildModel childModel2= new ChildModel();
        childModel2.setChecked(true);
        childModel2.setCount("100");
        childModel2.setHeader("Bedroom");
        childModel2.setId("1");
        childModel2.setLightOn(true);
        childModel2.setName("Reading Light 2");
        childModels.add(childModel2);



        HeaderModel headerModel = new HeaderModel();
        headerModel.setId("1");
        headerModel.setName("Lights");
        headerModel.setCount("2");
        HashMap<String,List<ChildModel>> strings = new HashMap<>();
        strings.put("Lights",childModels);
        headerModel.setChildDataList(strings);
        headerModels.add(headerModel);


        List<ChildModel> childModels1 = new ArrayList<>();

        ChildModel childModel3= new ChildModel();
        childModel3.setChecked(true);
        childModel3.setCount("100");
        childModel3.setHeader("Blinds");
        childModel3.setId("1");
        childModel3.setLightOn(true);
        childModel3.setName("Reading Blind 1");
        childModels1.add(childModel3);


        ChildModel childModel4= new ChildModel();
        childModel4.setChecked(true);
        childModel4.setCount("100");
        childModel4.setHeader("Blinds");
        childModel4.setId("1");
        childModel4.setLightOn(true);
        childModel4.setName("Reading Blind 2");
        childModels1.add(childModel4);

        ChildModel childModel5= new ChildModel();
        childModel5.setChecked(true);
        childModel5.setCount("100");
        childModel5.setHeader("Blinds");
        childModel5.setId("1");
        childModel5.setLightOn(true);
        childModel5.setName("Reading Blind 3");
        childModels1.add(childModel5);


        HeaderModel headerModel1 = new HeaderModel();
        headerModel1.setId("1");
        headerModel1.setName("Blinds");
        headerModel1.setCount("2");
        HashMap<String,List<ChildModel>> strings1 = new HashMap<>();
        strings1.put("Blinds",childModels1);
        headerModel1.setChildDataList(strings1);
        headerModels.add(headerModel1);



        DummyExpandableAdapter dummyExpandableAdapter = new DummyExpandableAdapter(DummyExpandableListActivity.this,headerModels);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
         int width = metrics.widthPixels;
        elList.setIndicatorBounds(width - GetDipsFromPixel(50), width - GetDipsFromPixel(10));
        elList.setAdapter(dummyExpandableAdapter);

    }
    public int GetDipsFromPixel(float pixels)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

}
