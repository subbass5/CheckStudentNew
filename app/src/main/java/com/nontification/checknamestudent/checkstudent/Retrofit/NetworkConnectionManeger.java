package com.nontification.checknamestudent.checkstudent.Retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nontification.checknamestudent.checkstudent.Fragment_login;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_del;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_load_data;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_save_data;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnectionManeger {

    public NetworkConnectionManeger() {
    }

    public void callServerLogin(final OnNetworkCallbackLoginListener listener, String username, String password) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Fragment_login.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APISERVER callapi = retrofit.create(APISERVER.class);

        Call call = callapi.loginHandle(username, password);
        call.enqueue(new Callback<POJO_login>() {

            @Override
            public void onResponse(Call<POJO_login> call, Response<POJO_login> response) {

                try {

                    POJO_login loginRes = (POJO_login) response.body();

                    if (response.code() != 200) {
//                        Log.e("Network connected","Response code = "+response.code());

                        ResponseBody responseBody = response.errorBody();

                        if (responseBody != null) {
                            listener.onBodyError(responseBody);
                        } else if (responseBody == null) {
                            listener.onBodyErrorIsNull();
                        }


//                        Toast.makeText(, ""+loginRes.getAccesstoken(), Toast.LENGTH_SHORT).show();
//                        Log.e("Network connected","Response code = "+loginRes.getAccesstoken());
                    } else {
                        listener.onResponse(loginRes);
                    }

                } catch (Exception e) {
                    Log.e("Network connect error1", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<POJO_login> call, Throwable t) {
                try {

                    listener.onFailure(t);

                } catch (Exception e) {

                    listener.onFailure(t);
//                    Log.e("Network connectLogin",t.getMessage());
                }

            }
        });
    }

    //////////////////////////////////////////////////////////////
    public void callServerdeldata(final OnNetworkCallbackFordel listener, String list_data) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Fragment_login.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APISERVER callapi = retrofit.create(APISERVER.class);

        Call call = callapi.Del_data(list_data);
        call.enqueue(new Callback<POJO_del>() {

            @Override
            public void onResponse(Call<POJO_del> call, Response<POJO_del> response) {

                try {

                    POJO_del pojo_del = (POJO_del) response.body();

                    if (response.code() != 200) {
//                        Log.e("Network connected","Response code = "+response.code());

                        ResponseBody responseBody = response.errorBody();

                        if (responseBody != null) {
                            listener.onBodyError(responseBody);
                        } else if (responseBody == null) {
                            listener.onBodyErrorIsNull();
                        }


//                        Toast.makeText(, ""+loginRes.getAccesstoken(), Toast.LENGTH_SHORT).show();
//                        Log.e("Network connected","Response code = "+loginRes.getAccesstoken());
                    } else {
                        listener.onResponse(pojo_del);
                    }

                } catch (Exception e) {
                    Log.e("Network connect error1", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<POJO_del> call, Throwable t) {
                try {

                    listener.onFailure(t);

                } catch (Exception e) {

                    listener.onFailure(t);
//                    Log.e("Network connectLogin",t.getMessage());
                }

            }
        });
    }
    ///////////////////////////////////////////////////////////////
    public void getlstdata (final OnNetworkCallbackLoadData listener,  String type) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Fragment_login.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APISERVER callapi = retrofit.create(APISERVER.class);

        Call call = callapi.load_data(type);


        call.enqueue(new Callback<List<POJO_load_data>>() {

            @Override
            public void onResponse(Call<List<POJO_load_data>> call, Response<List<POJO_load_data>> response) {
//                Log.e("onResponse",""+response.body());

                try {

                    List<POJO_load_data> pojo_load_data =  response.body();

                    if (response.code() != 200) {
//                        Log.e("Network connected","Response code = "+response.code());

                        ResponseBody responseBody = response.errorBody();

                        if (responseBody != null) {
                            listener.onBodyError(responseBody);
                        } else if (responseBody == null) {
                            listener.onBodyErrorIsNull();
                        }

//                        Toast.makeText(, ""+loginRes.getAccesstoken(), Toast.LENGTH_SHORT).show();
//                        Log.e("Network connected","Response code = "+loginRes.getAccesstoken());
                    } else {
                        listener.onResponse(pojo_load_data);
                    }

                } catch (Exception e) {
//                    Log.e("Network connect error",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<POJO_load_data>> call, Throwable t) {
                Log.e("NT", t.getMessage());
                try {

                    listener.onFailure(t);

                } catch (Exception e) {

                    listener.onFailure(t);
//                    Log.e("Network connectLogin",t.getMessage());
                }

            }
        });
    }
    ///////////////////////////////////////////////////////////////
    public void CallsaveData (final OnNetworkCallbackSaveData listener,  int member_id , String date_code , int activities_week ) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Fragment_login.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APISERVER callapi = retrofit.create(APISERVER.class);

        Call call = callapi.save_data(member_id,date_code,activities_week);


        call.enqueue(new Callback<POJO_save_data>() {

            @Override
            public void onResponse(Call<POJO_save_data> call, Response<POJO_save_data> response) {

                try {

                    POJO_save_data pojo_save_data = (POJO_save_data) response.body();

                    if (response.code() != 200) {
//                        Log.e("Network connected","Response code = "+response.code());

                        ResponseBody responseBody = response.errorBody();

                        if (responseBody != null) {
                            listener.onBodyError(responseBody);
                        } else if (responseBody == null) {
                            listener.onBodyErrorIsNull();
                        }


//                        Toast.makeText(, ""+loginRes.getAccesstoken(), Toast.LENGTH_SHORT).show();
//                        Log.e("Network connected","Response code = "+loginRes.getAccesstoken());
                    } else {
                        listener.onResponse(pojo_save_data);
                    }

                } catch (Exception e) {
                    Log.e("Network connect error1", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<POJO_save_data> call, Throwable t) {
                try {

                    listener.onFailure(t);

                } catch (Exception e) {

                    listener.onFailure(t);
//                    Log.e("Network connectLogin",t.getMessage());
                }

            }
        });
    }
}
