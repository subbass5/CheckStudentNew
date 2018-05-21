package com.nontification.checknamestudent.checkstudent.Retrofit;

import com.nontification.checknamestudent.checkstudent.POJO.POJO_load_data;

import java.util.List;

import okhttp3.ResponseBody;

public interface OnNetworkCallbackLoadData {

        public void onResponse(List<POJO_load_data> Checkdaily);
        public void onBodyError(ResponseBody responseBodyError);
        public void onBodyErrorIsNull();
        public void onFailure(Throwable t);
}
