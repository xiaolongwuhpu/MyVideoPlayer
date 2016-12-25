package com.longwu.ijkplayer.frgment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.data.MyData;
import com.longwu.ijkplayer.utlis.CommonDialog;
import com.longwu.ijkplayer.utlis.SharedPreferencesUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment4 extends Fragment {
    ImageView is_Need_Custom;
    Context mctx;
    public static String ISNEEDCUSTOM = "isneedcustom";
    boolean is_need_status;

    public MyFragment4() {
    }

    public static MyFragment4 newInstance(Context ctx) {
        MyFragment4 newFragment = new MyFragment4();
        Bundle bundle = new Bundle();
//        bundle.putString("name", ctx);
//        bundle.putString("passwd", passwd);
        newFragment.setArguments(bundle);
        return newFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content4, container, false);
        is_Need_Custom = (ImageView) view.findViewById(R.id.is_need_custom);
        is_need_status = SharedPreferencesUtils.getBoolean(mctx, ISNEEDCUSTOM);
        Custom_status();
        is_Need_Custom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                is_need_status = !is_need_status;
                if (is_need_status) {
                    final CommonDialog dialog = new CommonDialog(getActivity(),
                            null, 2);
                    dialog.setDialogListener(new CommonDialog.myDiaLogListener() {

                        public void onClick(View view) {
                            switch (view.getId()) {
                                case R.id.button_mydialog_enter:
                                    dialog.dismiss();
                                    break;

                                default:
                                    break;
                            }

                        }
                    });
                    dialog.setTitle("注意事项");
                    dialog.setMessage("把自己的.txt节目源文件复制到sd/longwuplayer目录下,文件格式必须如:CCTV1,http://192.XXX \n 节目的名字和url地址用\",\"逗号分隔开,每行只能有一条节目");
                    dialog.setOkButtonTitle("知道了");
                    dialog.show();
                }
                Custom_status();
            }
        });

        return view;
    }

    private void Custom_status() {
        if (is_need_status) {
            is_Need_Custom.setBackgroundResource(R.mipmap.slide_on);
            SharedPreferencesUtils.setBoolean(mctx, ISNEEDCUSTOM, true);

        } else {
            is_Need_Custom.setBackgroundResource(R.mipmap.slide_off);
            SharedPreferencesUtils.setBoolean(mctx, ISNEEDCUSTOM, false);
            List<String> list = Arrays.asList(MyData.main_RecycleList[3]);
        }
//        ceshi();
    }

    public void ceshi() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("哈哈哈哈");
//            }
//        }).start();
    }
}
