package com.nontification.checknamestudent.checkstudent.Retrofit;

import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_save_data;

import java.util.List;

import okhttp3.ResponseBody;

public interface OnNetworkCallbackSaveData {

    public void onResponse(POJO_save_data save_data);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
