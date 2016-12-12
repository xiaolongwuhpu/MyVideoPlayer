package com.longwu.ijkplayer.frgment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.TvMenuActivity;
import com.longwu.ijkplayer.adapter.MyAdapter;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard;
import com.longwu.ijkplayer.utlis.MyItemDivider;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends Fragment {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    public MyFragment1() {
    }
Context mctx;
    public static MyFragment1 newInstance(Context ctx) {
        MyFragment1 newFragment = new MyFragment1();
        Bundle bundle = new Bundle();
//        bundle.putString("name", ctx);
//        bundle.putString("passwd", passwd);
        newFragment.setArguments(bundle);
        return newFragment;

    }

    @SuppressLint("ValidFragment")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mctx = getContext();
        View view = inflater.inflate(R.layout.fg_content,container,false);
        mMyAdapter = new MyAdapter(mctx, MyData.getinstance().getMain_RecycleList());
//        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mctx, 3));
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyItemDivider(mctx, R.drawable.rv_main_item_divider));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                switch (position) {
                    case 0:
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 1:
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 2:
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        Toast.makeText(mctx, "正在挖掘资源中", Toast.LENGTH_LONG).show();
                        break;
                }

            }
        });

        AssetsCopyTOSDcard assetsCopyTOSDcard = new AssetsCopyTOSDcard(mctx);
        for (int i = 0; i < 3; i++) {
            assetsCopyTOSDcard.copyFilesFassets(mctx, stringsPath[i], stringsPath[i]);
        }
        return view;
    }
        String[] stringsPath = new String[]{"tv.txt","foreign_show.txt","sex.txt"};
    private void startActivity(Class<?> cls,int po) {
        Intent intent = new Intent(mctx, cls);
        intent.putExtra("type",po);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mctx);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mctx);
    }
}
