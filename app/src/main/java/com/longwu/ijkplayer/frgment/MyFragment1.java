package com.longwu.ijkplayer.frgment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.TvMenuActivity;
import com.longwu.ijkplayer.adapter.HeaderAndFooterAdapter;
import com.longwu.ijkplayer.bean.HomeItem;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard;
import com.longwu.ijkplayer.utlis.GlideImageLoader;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends BaseFragment {
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;
    //    private MyAdapter mMyAdapter;
//    private BaseQuickAdapter homeAdapter;
    private ArrayList<HomeItem> mDataList;

    private HeaderAndFooterAdapter headerAndFooterAdapter;
    private static final int PAGE_SIZE = 3;

    public MyFragment1() {
    }

    Context mctx;
    private static MyFragment1 myFragment1;

    public static MyFragment1 newInstance(Context ctx) {
        if (myFragment1 == null) {
            myFragment1 = new MyFragment1();
        }
        Bundle bundle = new Bundle();
//        bundle.putString("name", ctx);
//        bundle.putString("passwd", passwd);
        myFragment1.setArguments(bundle);
        return myFragment1;

    }

    private static final Class<?>[] ACTIVITY = {TvMenuActivity.class};
    private static final String[] TITLE = MyData.main_RecycleList;
    private static final int[] IMG = {R.mipmap.gv_item_click, R.mipmap.gv_item_click, R.mipmap.gv_item_click, R.mipmap.gv_item_click, R.mipmap.gv_item_click, R.mipmap.gv_item_click, R.mipmap.gv_item_click};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataList();
    }

    private void initDataList() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[0]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }

    @SuppressLint("ValidFragment")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mctx = getContext();
        View view = inflater.inflate(R.layout.fg_content, container, false);
//        mMyAdapter = new MyAdapter(mctx, MyData.getinstance().getMain_RecycleList());

        //---------------------
/*        homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();*/
        //----------------------

        mRecyclerView = (RecyclerView) view.findViewById(R.id.first_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mctx, 4));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(227, 23, 189));

//        mMyAdapter.CanUse(3);
//        mMyAdapter.list_CanUseposition.add(5);
//        mMyAdapter.list_CanUseposition.add(6);
//        mMyAdapter.list_CanUseposition.add(7);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new MyItemDivider(mctx, R.drawable.rv_main_item_divider));

        //----------------
    /*    homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ACTIVITY[0], position);
            }
        });

        mRecyclerView.setAdapter(homeAdapter);*/
        initRefreshLayout();
//-----------------------------
        initAdapter();

        View headerView = getHeaderView(0, new OnClickListener() {
            @Override
            public void onClick(View v) {
//                headerAndFooterAdapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
            }
        });
        headerAndFooterAdapter.addHeaderView(headerView);
        mRecyclerView.setAdapter(headerAndFooterAdapter);


//        mMyAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View parent, int position) {
//
//                switch (position) {
//                    case 0:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 1:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 2:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 3:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 4:
//                        Toast.makeText(mctx, "正在挖掘资源中", Toast.LENGTH_LONG).show();
//                        break;
//                    case 5:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 6:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                    case 7:
//                        startActivity(TvMenuActivity.class, position);
//                        break;
//                }
//
//            }
//        });

        AssetsCopyTOSDcard assetsCopyTOSDcard = new AssetsCopyTOSDcard(mctx);
        for (int i = 0; i < stringsPath.length; i++) {
            assetsCopyTOSDcard.copyFilesFassets(mctx, stringsPath[i], stringsPath[i]);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private View getHeaderView(int type, OnClickListener listener) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.banner_item, (ViewGroup) mRecyclerView.getParent(), false);
//        if (type == 1) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
//            imageView.setImageResource(R.mipmap.rm_icon);
//        }
        imagelist.add("http://img.zcool.cn/community/018ede568f4d986ac725af23175492.jpg@2o.jpg");
        imagelist.add("http://img.zcool.cn/community/01a3f158be1d6fa801219c77d8af1c.png");
        imagelist.add("http://img.zcool.cn/community/018f94592e27bba801216a3e7e4501.jpg");
        Banner bannerimageView = (Banner) view.findViewById(R.id.banner);
        bannerimageView.setImageLoader(new GlideImageLoader());
        bannerimageView.setImages(imagelist);
        bannerimageView.start();
        view.setOnClickListener(listener);
        return view;
    }

    List<String> imagelist = new ArrayList<>();

    private void initAdapter() {


        headerAndFooterAdapter = new HeaderAndFooterAdapter(R.layout.home_item_view, mDataList);
        headerAndFooterAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(headerAndFooterAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
//            }
//        });
        headerAndFooterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(ACTIVITY[0], position);
//                adapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
//                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {

    }

    @Override
    public int setFragmentLayoutID() {
        return R.layout.fg_content;
    }

    String[] stringsPath = new String[]{"tv.txt", "foreign_show.txt", "sex.txt", "japan_video.txt", "sport.txt", "tvplay.txt"};

    private void startActivity(Class<?> cls, int po) {
        Intent intent = new Intent(mctx, cls);
        intent.putExtra("type", po);
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

    // 打电话权限申请的请求码
    private static final int CALL_PHONE_REQUEST_CODE = 0x0011;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refresh();
//                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),"刷新了.哈哈哈",Toast.LENGTH_LONG).show();
            }
        });
    }
}
