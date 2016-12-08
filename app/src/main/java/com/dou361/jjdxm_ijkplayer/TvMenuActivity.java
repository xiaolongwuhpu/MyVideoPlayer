package com.dou361.jjdxm_ijkplayer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dou361.jjdxm_ijkplayer.adapter.MyAdapter;
import com.dou361.jjdxm_ijkplayer.data.MyData;
import com.dou361.jjdxm_ijkplayer.utlis.MyItemDivider;

import java.util.List;


public class TvMenuActivity extends Activity {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<String> list_name;
    private List<String> list_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvmenu);


        Intent it = getIntent();
        int type = it.getIntExtra("type", 0);
        if (type == 0) {
            MyData.getinstance().getTxtFilelist("tv.txt");
        } else if (type == 1) {
            MyData.getinstance().getTxtFilelist("foreign_show.txt");
        }
        else if (type == 2) {
            MyData.getinstance().getTxtFilelist("sex.txt");
        }
        list_name = MyData.getinstance().list1;
        list_value = MyData.getinstance().list2;
        mMyAdapter = new MyAdapter(TvMenuActivity.this, list_name);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyItemDivider(this, R.drawable.rv_main_item_divider));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Intent intent = new Intent(TvMenuActivity.this, PlayerActivity.class);
                intent.putExtra("title"/*MyData.getinstance().getNameData().get(position)*/, list_name.get(position));
                intent.putExtra("url"/*MyData.getinstance().getNameData().get(position)*/, list_value.get(position));
                startActivity(intent);
            }
        });
    }
}
