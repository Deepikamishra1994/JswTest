package com.loyality.jsw.serverrequesthandler.models;

import java.io.Serializable;

public class ApproveFabricator implements Serializable {
    private String lastName;

    private String partnerCode;

    private String gender;

    private String city;

    private String emergencyNo;

    private String outletName;

    private String firstName;

    private String bloodGroup;

    private String dob;

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    private  String pincode;

    private String district;

    private String taluka;

    private String addressLine1;

    private String addressLine2;

    private String state;

    private String region;

    private String partnerMobile;

    private String status;
    private String date;

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getPartnerCode ()
    {
        return partnerCode;
    }

    public void setPartnerCode (String partnerCode)
    {
        this.partnerCode = partnerCode;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getEmergencyNo ()
    {
        return emergencyNo;
    }

    public void setEmergencyNo (String emergencyNo)
    {
        this.emergencyNo = emergencyNo;
    }

    public String getOutletName ()
    {
        return outletName;
    }

    public void setOutletName (String outletName)
    {
        this.outletName = outletName;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getBloodGroup ()
    {
        return bloodGroup;
    }

    public void setBloodGroup (String bloodGroup)
    {
        this.bloodGroup = bloodGroup;
    }

    public String getDob ()
    {
        return dob;
    }

    public void setDob (String dob)
    {
        this.dob = dob;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getTaluka ()
    {
        return taluka;
    }

    public void setTaluka (String taluka)
    {
        this.taluka = taluka;
    }

    public String getAddressLine1 ()
    {
        return addressLine1;
    }

    public void setAddressLine1 (String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2 ()
    {
        return addressLine2;
    }

    public void setAddressLine2 (String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getPartnerMobile ()
    {
        return partnerMobile;
    }

    public void setPartnerMobile (String partnerMobile)
    {
        this.partnerMobile = partnerMobile;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
