package com.loyality.jsw.serverrequesthandler.models;

public class RegisterModel {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmergencyNo() {
        return emergencyNo;
    }

    public void setEmergencyNo(String emergencyNo) {
        this.emergencyNo = emergencyNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMonthlyVolume() {
        return monthlyVolume;
    }

    public void setMonthlyVolume(String monthlyVolume) {
        this.monthlyVolume = monthlyVolume;
    }

    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emergencyNo;
    private String dob;
    private String bloodGroup;
    private String state;
    private String city;
    private String taluka;
    private String address;
    private String district;
    private String pincode;
    private String monthlyVolume;
    private String id;

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    private String outletName;


    private String retailerId;
    private String tmAssignedId;
    private String tmAssignedName;
    private String tmAssignedMobileNo;


    public String getTmAssignedName() {
        return tmAssignedName;
    }

    public void setTmAssignedName(String tmAssignedName) {
        this.tmAssignedName = tmAssignedName;
    }

    public String getTmAssignedMobileNo() {
        return tmAssignedMobileNo;
    }

    public void setTmAssignedMobileNo(String tmAssignedMobileNo) {
        this.tmAssignedMobileNo = tmAssignedMobileNo;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

    public String getTmAssignedId() {
        return tmAssignedId;
    }

    public void setTmAssignedId(String tmAssignedId) {
        this.tmAssignedId = tmAssignedId;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getTmMobileNo() {
        return tmMobileNo;
    }

    public void setTmMobileNo(String tmMobileNo) {
        this.tmMobileNo = tmMobileNo;
    }

    private String tmName;
    private String tmMobileNo;

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    private String distributorId;
    private String distributorName;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
