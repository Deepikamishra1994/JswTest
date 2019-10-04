package com.loyality.jsw.serverrequesthandler;

/**
 * Created by sahil.goyal on 3/13/2018.
 */

public class ErrorDto {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String error;

    public String description;
}
