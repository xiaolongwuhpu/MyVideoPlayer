package com.longwu.ijkplayer.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.adapter.MyDreamAdapter;
import com.longwu.ijkplayer.bean.Result;
import com.longwu.ijkplayer.module.ApiServiceUtils;
import com.longwu.ijkplayer.utlis.MyItemDivider;

import java.util.ArrayList;
import java.util.List;

import anet.channel.util.StringUtils;

public class DreamActivity extends Activity {
    RecyclerView mRecyclerView;
    private MyDreamAdapter mMyAdapter;
    List<Result> list;
    List<String> list_name;
    Context ctx;
    EditText dm_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream);
        ctx = this;
        dm_et = (EditText) findViewById(R.id.dm_et);
        mRecyclerView = (RecyclerView) findViewById(R.id.first_recyclerview);

        CharSequence hint = dm_et.getHint();
        SpannableString ss =  new SpannableString(hint);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);
//        dm_et.setHintTextColor(fontColor);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        dm_et.setHint(new SpannedString(ss));

    }

    public void dm_btn(View v){
        final String str = dm_et.getText().toString().trim();
        if(!StringUtils.isBlank(str)){
            new Thread() {
                @Override
                public void run() {
                    list = ApiServiceUtils.getReultList(str);
                    list_name = new ArrayList<String>();
//                for(int i = 0;i<list.size();i++){
//                    list_name.add(list.get(i).getName());
//                }
                    if(list.size()>0){
                        mHandler.sendEmptyMessage(0);
                    }else{
                        mHandler.sendEmptyMessage(1);

                    }

                }
            }.start();
        }else{
            Toast.makeText(ctx,"请输关键字",Toast.LENGTH_SHORT).show();
        }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0)
            {
                mMyAdapter = new MyDreamAdapter(ctx, list);

                mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx, LinearLayout.VERTICAL, false));
                mRecyclerView.setAdapter(mMyAdapter);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.addItemDecoration(new MyItemDivider(ctx, R.drawable.rv_main_item_divider2));
                mMyAdapter.setOnItemClickListener(new MyDreamAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View parent, int position) {
//                        Intent intent = new Intent(ctx, DreamItemActivity.class);
//                        intent.putExtra("dream_item_name", (Parcelable) list.get(position));
//                        startActivity(intent);
                    }
                });
            }else if(msg.what ==1){
                Toast.makeText(ctx,"没有找到相应的梦,周公也迷茫了",Toast.LENGTH_LONG).show();
            }

        }
    };
}
