package com.longwu.ijkplayer.module;

import com.alibaba.fastjson.JSON;
import com.longwu.ijkplayer.bean.LiveBean;
import com.longwu.ijkplayer.bean.Result;

import org.json.JSONObject;

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

    public static List<Result> getReultList() {
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
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService mApiServicePresenter = retrofit.create(ApiService.class);
        Call<String> call = mApiServicePresenter.dream("39bc9c14927bb5a5469a34931a6cd333");
        Response<String> response = null;
        try {
            response = call.execute();
            String body = response.body();
            JSONObject js = new JSONObject(body);
            if (body != null) {
                List<Result> temp = JSON.parseArray(js.getJSONArray("result").toString(), Result.class);
                list.addAll(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
