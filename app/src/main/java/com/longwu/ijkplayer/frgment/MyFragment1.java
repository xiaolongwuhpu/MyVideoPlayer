package com.longwu.ijkplayer.frgment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.longwu.ijkplayer.listener.PermissionsResultListener;
import com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard;
import com.longwu.ijkplayer.utlis.MyItemDivider;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends BaseFragment {
    RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;
    public MyFragment1() {
    }
Context mctx;
    private static MyFragment1 myFragment1;
    public static MyFragment1 newInstance(Context ctx) {
        if(myFragment1 == null){
            myFragment1 = new MyFragment1();
        }
        Bundle bundle = new Bundle();
//        bundle.putString("name", ctx);
//        bundle.putString("passwd", passwd);
        myFragment1.setArguments(bundle);
        return myFragment1;

    }

    @SuppressLint("ValidFragment")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mctx = getContext();
        View view = inflater.inflate(R.layout.fg_content,container,false);
        mMyAdapter = new MyAdapter(mctx, MyData.getinstance().getMain_RecycleList());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mctx, 3));
        mMyAdapter.CanUse(3);
        mMyAdapter.list_CanUseposition.add(5);
        mMyAdapter.list_CanUseposition.add(6);
        mMyAdapter.list_CanUseposition.add(7);
        mRecyclerView.setAdapter(mMyAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new MyItemDivider(mctx, R.drawable.rv_main_item_divider));



        performRequestPermissions("权限声明",new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE}
                , CALL_PHONE_REQUEST_CODE, new PermissionsResultListener() {
                    @Override
                    public void onPermissionGranted() {
//                        Toast.makeText(mctx, "已申请权限", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(mctx, "拒绝申请权限", Toast.LENGTH_LONG).show();
                    }
                });

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
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 4:
                        Toast.makeText(mctx, "正在挖掘资源中", Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 6:
                        startActivity(TvMenuActivity.class, position);
                        break;
                    case 7:
                        startActivity(TvMenuActivity.class, position);
                        break;
                }

            }
        });

        AssetsCopyTOSDcard assetsCopyTOSDcard = new AssetsCopyTOSDcard(mctx);
        for (int i = 0; i < stringsPath.length; i++) {
            assetsCopyTOSDcard.copyFilesFassets(mctx, stringsPath[i], stringsPath[i]);
        }
        return view;
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

    String[] stringsPath = new String[]{"tv.txt","foreign_show.txt","sex.txt","japan_video.txt","sport.txt","tvplay.txt"};
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

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionHelper.requestPermissionsResult(this,
//                BaseActivity.SD_REQUEST_CODE,permissions);
//    }
//    @PermissionSucceed(requestCode =  BaseActivity.SD_REQUEST_CODE)
//    private void getSD() {
//        Toast.makeText(mctx,"sd卡成功",Toast.LENGTH_LONG).show();
//    }
//
//    @PermissionFail(requestCode = BaseActivity.SD_REQUEST_CODE)
//    private void getSDFail(){
//
//              Toast.makeText(mctx,"您拒绝了sd卡",
//                Toast.LENGTH_SHORT).show();
//    }

    // 打电话权限申请的请求码
    private static final int CALL_PHONE_REQUEST_CODE = 0x0011;

/*
     @PermissionSucceed(requestCode =  CALL_PHONE_REQUEST_CODE)
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:10086");
        intent.setData(data);
        startActivity(intent);
    }

    @PermissionFail(requestCode = CALL_PHONE_REQUEST_CODE)
    private void callPhoneFail(){
        Toast.makeText(mctx,"您拒绝了拨打电话",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        List<Fragment> fragments = getChildFragmentManager().getFragments();
//        if (fragments != null) {
//            for (Fragment fragment : fragments) {
//                if (fragment != null) {
//                    fragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
//                }
//            }
//        }

        PermissionHelper.requestPermissionsResult(myFragment1,requestCode,permissions);
    }*/
}
