package com.longwu.ijkplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by longwu on 2016/12/03.
 */
public class SelectScreenActivity extends BaseActivity implements View.OnClickListener {
static  String myurl,tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_screen);
        ButterKnife.bind(this);
        getintentData();
    }

    private void getintentData() {
        Intent it = getIntent();
        myurl = it.getStringExtra("url");
        tv_title = it.getStringExtra("title");
    }

    @OnClick({R.id.btn_h, R.id.btn_v/*, R.id.btn_live*/, R.id.btn_origin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_h:
                /**半屏播放器*/
                startActivity(HPlayerActivity.class);
                break;
            case R.id.btn_v:
                /**竖屏播放器*/
                startActivity(PlayerActivity.class);
                break;
          /*  case R.id.btn_live:
                *//**竖屏直播播放器*//*
                startActivity(PlayerLiveActivity.class);
                break;*/
            case R.id.btn_origin:
                /**ijkplayer原生的播放器*/
                startActivity(OriginPlayerActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(SelectScreenActivity.this, cls);
        startActivity(intent);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
