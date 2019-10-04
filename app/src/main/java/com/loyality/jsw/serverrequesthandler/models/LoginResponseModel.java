package com.loyality.jsw.serverrequesthandler.models;

public class LoginResponseModel {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String token;
private String userType;


}
