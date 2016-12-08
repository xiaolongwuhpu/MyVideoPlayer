package com.dou361.jjdxm_ijkplayer.module;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


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


}
