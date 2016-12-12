package com.longwu.ijkplayer.module;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * ========================================
 Created by longwu on 2016/12/03.
 * ========================================
 */
public interface ApiService {

    /**
     * 直播相关接口
     */
    @FormUrlEncoded
    @POST("/kkgame/entrance")
    Call<String> live(@Field("parameter") String params);

    /**
     * 直播相关接口
     */
    @GET("/dream/category?key={key}")
    Call<String> dream(@Query("key") String params);

}
