package com.longwu.ijkplayer.frgment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.adapter.MyAdapter;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.ui.DreamActivity;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment2 extends Fragment {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    public MyFragment2() {
    }
Context ctx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
         mRecyclerView = (RecyclerView) view.findViewById(R.id.first_recyclerview);
        ctx=getContext();

        mMyAdapter = new MyAdapter(ctx, MyData.getinstance().getSever_RecycleList());
        mMyAdapter.list_CanUseposition.add(1);
        GridLayoutManager gm = new GridLayoutManager(ctx,3, LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(/*new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)*/gm);
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL_LIST));
        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Intent intent = new Intent();
                switch(position)
                {
                    case 0:
//                        intent.setClass(ctx, PlayerActivity.class);
                        break;
                    case 1:
                        intent.setClass(ctx, DreamActivity.class);
                        startActivity(intent);
                        break;
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    case 6:
//                    case 7:
//                    case 8:
//                    case 9:
//                    case 10:
//                        intent.setClass(ctx, PlayerActivity.class);
//                        break;
                }
//                startActivity(intent);
            }
        });

        txt_content.setText("NIHAO"/*list.toString()*/);
        txt_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        return view;
    }

}
