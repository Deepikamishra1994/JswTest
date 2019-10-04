package com.loyality.jsw.serverrequesthandler.models;

import java.util.List;

public class SizeUnitModel {
    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getUnits() {
        return units;
    }

    public void setUnits(List<String> units) {
        this.units = units;
    }

    private List<String> sizes;
    private List<String> units;
}
