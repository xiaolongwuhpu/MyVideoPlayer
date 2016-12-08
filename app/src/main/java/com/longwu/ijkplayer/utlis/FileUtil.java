package com.longwu.ijkplayer.utlis;

import android.os.Environment;

import java.io.File;

/**
 * Created by 龙五 on 2016/12/6.
 */
public class FileUtil {
    /**
     * 播放本地视频
     */
    public static String getLocalVideoPath(String name) {
        String sdCard = Environment.getExternalStorageDirectory().getPath();
        String uri = sdCard + File.separator + name;
        return uri;
    }
}
