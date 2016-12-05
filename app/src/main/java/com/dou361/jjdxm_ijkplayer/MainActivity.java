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

public class MainActivity extends Activity {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyAdapter = new MyAdapter(MainActivity.this, MyData.getinstance().getMain_RecycleList());
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyItemDivider(this,R.drawable.rv_main_item_divider));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                switch (position)
                {
                    case 0:
                        startActivity(TvMenuActivity.class);
                        break;
                    case 1:
                        startActivity(TvMenuActivity.class);
                        break;
                }

            }
        });
    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}
