package com.longwu.ijkplayer.module;

import com.alibaba.fastjson.JSON;
import com.longwu.ijkplayer.bean.LiveBean;
import com.longwu.ijkplayer.bean.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * ========================================
 Created by longwu on 2016/12/03.
 * ========================================
 */
public class ApiServiceUtils {

    static String jh_APPKEY="39bc9c14927bb5a5469a34931a6cd333";
    public static List<LiveBean> getLiveList() {
        List<LiveBean> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apikg.kktv1.com:8080")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService mApiServicePresenter = retrofit.create(ApiService.class);
        Call<String> call = mApiServicePresenter.live("{\"platform\":2,\"count\":2,\"start\":0,\"c\":90013,\"FuncTag\":80010001,\"a\":10}");
        Response<String> response = null;
        try {
            response = call.execute();
            String body = response.body();
            JSONObject js = new JSONObject(body);
            if (body != null) {
                List<LiveBean> temp = JSON.parseArray(js.getJSONArray("roomList").toString(), LiveBean.class);
                list.addAll(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Result> getReultList(String str) {
        String urlStr = null;
        try {
             urlStr = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Result> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService mApiServicePresenter = retrofit.create(ApiService.class);
        Call<String> call = mApiServicePresenter.dream(str,0,1,"39bc9c14927bb5a5469a34931a6cd333");
        Response<String> response = null;
        try {
            response = call.execute();
            String body = response.body();
            JSONObject js = new JSONObject(body);
            if (body != null) {
               if( js.has("result")&& !js.isNull("result"))
               {
                JSONArray js_arr = js.getJSONArray("result");
                    List<Result> temp = JSON.parseArray(js_arr.toString(), Result.class);
                    list.addAll(temp);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
