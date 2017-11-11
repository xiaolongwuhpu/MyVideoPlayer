package com.longwu.ijkplayer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.widget.Toast;

import com.longwu.ijkplayer.utlis.MPermissionsActivity;
import com.longwu.ijkplayer.utlis.permission.PermissionFail;
import com.longwu.ijkplayer.utlis.permission.PermissionSucceed;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgent.EScenarioType;


public class BaseActivity extends MPermissionsActivity {
    Context mContext;
    private final String mPageName = "BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 经测试在代码里直接声明透明状态栏更有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mContext = this;
        MobclickAgent.setDebugMode(true);
        // SDK在统计Fragment时，需要关闭Activity自带的页面统计，
        // 然后在每个页面中重新集成页面统计的代码(包括调用了 onResume 和 onPause 的Activity)。
        MobclickAgent.openActivityDurationTrack(false);

        MobclickAgent.setScenarioType(mContext, EScenarioType.E_UM_NORMAL);
//
//        if (ContextCompat.checkSelfPermission(mContext, "android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    SD_REQUEST_CODE);
//        }


//        requestPermission(new String[]{permission.WRITE_EXTERNAL_STORAGE}, SD_REQUEST_CODE);

//        PermissionHelper.with(this).requestCode(CALL_PHONE_REQUEST_CODE)
//                .requestPermission(Manifest.permission.CALL_PHONE).request();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(mPageName);
        MobclickAgent.onResume(mContext);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(mPageName);
        MobclickAgent.onPause(mContext);
    }
//    public static  int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 200;

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        doNext(requestCode,grantResults);
//    }
//    private void doNext(int requestCode, int[] grantResults) {
//        if (requestCode == SD_REQUEST_CODE) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission Granted
//                Toast.makeText(mContext,"sd卡权限正常",Toast.LENGTH_LONG).show();
//            } else {
//
//                // Permission Denied
//                Toast.makeText(mContext,"sd卡权限被禁止,请打开后使用app",Toast.LENGTH_LONG).show();
//            }
//        }
//    }


    // 打电话权限申请的请求码
    public static final int CALL_PHONE_REQUEST_CODE = 0x0011;
    //sd卡权限
    public static final int SD_REQUEST_CODE = 0x0012;

    @PermissionSucceed(requestCode =  CALL_PHONE_REQUEST_CODE)
    private void callPhone() {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:10086");
//        intent.setData(data);
//        startActivity(intent);
    }

    @PermissionFail(requestCode = CALL_PHONE_REQUEST_CODE)
    private void callPhoneFail(){

        showTipsDialog();
        Toast.makeText(this,"您拒绝了拨打电话",
                Toast.LENGTH_SHORT).show();
    }




//    /**
//     * 权限成功回调函数
//     *
//     * @param requestCode
//     */
//    @Override
//    public void permissionSuccess(int requestCode) {
//        super.permissionSuccess(requestCode);
//        switch (requestCode) {
//            case 0x0001:
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
//                startActivity(intent);
//                break;
//            case SD_REQUEST_CODE:
//
//                break;
//        }
//
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        PermissionHelper.requestPermissionsResult(this,
//                CALL_PHONE_REQUEST_CODE,permissions);
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}
