package com.longwu.ijkplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.longwu.ijkplayer.adapter.MyAdapter;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard;
import com.longwu.ijkplayer.utlis.MyItemDivider;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends BaseActivity {
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
        mRecyclerView.addItemDecoration(new MyItemDivider(this, R.drawable.rv_main_item_divider));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                switch (position)
                {
                    case 0:
                        startActivity(TvMenuActivity.class,position);
                        break;
                    case 1:
                        startActivity(TvMenuActivity.class,position);
                        break;
                    case 2:
                        startActivity(TvMenuActivity.class,position);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        Toast.makeText(MainActivity.this,"正在挖掘资源中",Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });

        com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard assetsCopyTOSDcard=new AssetsCopyTOSDcard(getApplicationContext());
        for (int i = 0;i<3;i++)
        {
            assetsCopyTOSDcard.copyFilesFassets(this,stringsPath[i], stringsPath[i]);
//        assetsCopyTOSDcard.AssetToSD(stringsPath[i], Environment.getExternalStorageDirectory().toString()+"/aliPayLoad/"+stringsPath[i]);
}
    }
String[] stringsPath = new String[]{"tv.txt","foreign_show.txt","sex.txt"};
    private void startActivity(Class<?> cls,int po) {
        Intent intent = new Intent(MainActivity.this, cls);
        intent.putExtra("type",po);
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
