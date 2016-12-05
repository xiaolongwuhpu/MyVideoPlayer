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

public class TvMenuActivity extends Activity {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvmenu);
        MyData.getinstance().getTxtFilelist("");
        mMyAdapter = new MyAdapter(TvMenuActivity.this, MyData.getinstance().list1);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyItemDivider(this,R.drawable.rv_main_item_divider));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Intent intent = new Intent(TvMenuActivity.this, SelectScreenActivity.class);
                intent.putExtra("title"/*MyData.getinstance().getNameData().get(position)*/,MyData.getinstance().list1.get(position) );
                intent.putExtra("url"/*MyData.getinstance().getNameData().get(position)*/, MyData.getinstance().list2.get(position));
                startActivity(intent);
            }
        });
    }
}
