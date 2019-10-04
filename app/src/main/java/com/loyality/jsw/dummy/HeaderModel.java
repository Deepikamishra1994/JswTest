package com.loyality.jsw.dummy;

import java.util.HashMap;
import java.util.List;

public class HeaderModel {

    private String name;
    private String id;
    private String count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private HashMap<String, List<ChildModel>> childDataList = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public HashMap<String, List<ChildModel>> getChildDataList() {
        return childDataList;
    }

    public void setChildDataList(HashMap<String, List<ChildModel>> childDataList) {
        this.childDataList = childDataList;
    }
}
