package com.longwu.ijkplayer.frgment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longwu.ijkplayer.R;
import com.longwu.ijkplayer.utlis.SharedPreferencesUtils;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment4 extends Fragment {
    ImageView is_Need_Custom;
    Context mctx;
    TextView textView;
   public static String  ISNEEDCUSTOM= "isneedcustom";
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
        View view = inflater.inflate(R.layout.fg_content4,container,false);
        is_Need_Custom = (ImageView) view.findViewById(R.id.is_need_custom);
        textView = (TextView) view.findViewById(R.id.ceshi);
        is_need_status =  SharedPreferencesUtils.getBoolean(mctx,ISNEEDCUSTOM);
        Custom_status();
        is_Need_Custom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                is_need_status = !is_need_status;
                Custom_status();
            }
        });

        return view;
    }

    private void Custom_status() {
        if (is_need_status) {
            is_Need_Custom.setBackgroundResource(R.mipmap.slide_on);
            SharedPreferencesUtils.setBoolean(mctx,ISNEEDCUSTOM,true);
        } else {
            is_Need_Custom.setBackgroundResource(R.mipmap.slide_off);
            SharedPreferencesUtils.setBoolean(mctx,ISNEEDCUSTOM,false);
        }
        ceshi();
    }

    public void ceshi()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("哈哈哈哈");
            }
        }).start();
    }
}
