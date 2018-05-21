package com.nontification.checknamestudent.checkstudent.Retrofit;


import com.nontification.checknamestudent.checkstudent.POJO.POJO_del;

import okhttp3.ResponseBody;

public interface OnNetworkCallbackFordel {

    public void onResponse(POJO_del del);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
