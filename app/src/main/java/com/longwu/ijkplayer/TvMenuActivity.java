package com.longwu.ijkplayer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.longwu.ijkplayer.adapter.MyAdapter;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.utlis.MyItemDivider;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.List;

/**
 * Created by longwu on 2016/12/03.
 */
public class TvMenuActivity extends BaseActivity {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    private List<String> list_name;
    private List<String> list_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvmenu);

        TextView id_tx = (TextView) findViewById(R.id.id_tx);
        Intent it = getIntent();
        int type = it.getIntExtra("type", 0);
        if (type == 0) {
            MyData.getinstance().getTxtFilelist("tv.txt");
        } else if (type == 1) {
            MyData.getinstance().getTxtFilelist("foreign_show.txt");
        }
        else if (type == 2) {
            MyData.getinstance().getTxtFilelist("sex.txt");
        } else if (type == 3) {
            MyData.getinstance().getTxtFilelist("japan_video.txt");
            id_tx.setVisibility(View.VISIBLE);
        }else if (type == 5) {
            MyData.getinstance().getTxtFilelist("tvplay.txt");
//            id_tx.setVisibility(View.VISIBLE);
        }
        else if (type == 6) {
            MyData.getinstance().getTxtFilelist("sport.txt");
//            id_tx.setVisibility(View.VISIBLE);
        }
        else if (type == 7) {
            File[] files = new File(MyData.rootdir).listFiles();
            if(files.length>0){
                MyData.getinstance().getTxtFilelist(files[0].getName(),true);
            }else{
                MyData.getinstance().getTxtFilelist("me.txt",true);
            }

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
