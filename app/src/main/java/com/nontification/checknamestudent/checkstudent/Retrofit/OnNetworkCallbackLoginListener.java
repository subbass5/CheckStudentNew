package com.nontification.checknamestudent.checkstudent.Retrofit;

import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;

import okhttp3.ResponseBody;

public interface OnNetworkCallbackLoginListener {

    public void onResponse(POJO_login loginRes);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
