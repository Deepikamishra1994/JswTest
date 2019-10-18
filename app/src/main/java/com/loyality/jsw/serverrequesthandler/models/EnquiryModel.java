package com.loyality.jsw.serverrequesthandler.models;

import java.io.Serializable;

public class EnquiryModel implements Serializable {
    private String lastName;

    private String city;

    private String query;

    private String queryId;

    private String emergencyNo;

    private String outletName;

    private String firstName;

    private String queryDate;

    private String addressLine1;

    private String addressLine2;

    private String state;

    private String partnerMobile;

    private String status;

    public String getRemarks() {
        return remark;
    }

    public void setRemarks(String remarks) {
        this.remark = remarks;
    }

    private String remark;

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getQuery ()
    {
        return query;
    }

    public void setQuery (String query)
    {
        this.query = query;
    }

    public String getQueryId ()
    {
        return queryId;
    }

    public void setQueryId (String queryId)
    {
        this.queryId = queryId;
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

    public String getQueryDate ()
    {
        return queryDate;
    }

    public void setQueryDate (String queryDate)
    {
        this.queryDate = queryDate;
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

}
