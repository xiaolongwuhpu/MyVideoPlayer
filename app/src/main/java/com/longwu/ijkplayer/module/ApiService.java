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
     * http://v.juhe.cn/dream/query
     * q=%E5%A5%B3%E4%BA%BA&cid=&full=1&key=39bc9c14927bb5a5469a34931a6cd333
     */
    @GET("/dream/query?")
    Call<String> dream(@Query("q") String q,@Query("cid") int cid,@Query("full") int full,@Query("key") String key);

}
