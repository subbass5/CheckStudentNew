package com.nontification.checknamestudent.checkstudent.Retrofit;

import com.nontification.checknamestudent.checkstudent.POJO.POJO_del;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_load_data;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_login;
import com.nontification.checknamestudent.checkstudent.POJO.POJO_save_data;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APISERVER {

    @FormUrlEncoded
    @POST("login1/")
    Call<POJO_login> loginHandle(@Field("username") String first, @Field("password") String last);


    @FormUrlEncoded
    @POST("cancel_check/")
    Call<POJO_del> Del_data (@Field("list_data") String first);

    @FormUrlEncoded
    @POST("load_history_check/")
    Call<POJO_load_data> load_data (@Field("type") String first);

    @FormUrlEncoded
    @POST("api-save-data/")
    Call<POJO_save_data> save_data (@Field("member_id") int first , @Field("date_code") String sec , @Field("activities_week") int thir);
}
