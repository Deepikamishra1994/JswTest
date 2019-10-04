package com.loyality.jsw.serverrequesthandler;



/**
 * Created by sahil.goyal on 3/13/2018.
 */

public interface GetDispatchs<T> {

    public void apiSuccess(T body, ResponseTypes response);
    public void apiError(ErrorDto error);
    public void error(String body);

}
