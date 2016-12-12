package com.longwu.ijkplayer.utlis;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.longwu.ijkplayer.frgment.MyFragment4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by 龙五 on 2016/12/8.
 */
public class AssetsCopyTOSDcard {
    Context context;

    public AssetsCopyTOSDcard(Context context) {
        super();
        this.context = context;
    }
    /**
     *  从assets目录中复制整个文件夹内容
     *  @param  context  Context 使用CopyFiles类的Activity
     *  @param  oldPath  String  原文件路径  如：/aa
     *  @param  newfilename  String  复制后路径  如：xx:/bb/cc
     */
    public void copyFilesFassets(Context context,String oldPath,String newfilename) {
        try {
            String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
            if (fileNames.length > 0) {//如果是目录
                File file = new File(newfilename);
                file.mkdirs();//如果文件夹不存在，则递归
                for (String fileName : fileNames) {
                    copyFilesFassets(context,oldPath + "/" + fileName,newfilename+"/"+fileName);
                }
            } else {//如果是文件

                String filepath = Environment.getExternalStorageDirectory().toString()+"/aliPayLoad/";
               boolean isneed_Copy =  isneed_CopyFile(filepath,newfilename);

                if(!SharedPreferencesUtils.getBoolean(context, MyFragment4.ISNEEDCUSTOM)){
                    InputStream is = context.getAssets().open(oldPath);
                    FileOutputStream fos = new FileOutputStream(filepath+newfilename);
                    byte[] buffer = new byte[1024];
                    int byteCount=0;
                    while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
                        fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
                    }
                    fos.flush();//刷新缓冲区
                    is.close();
                    fos.close();
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //如果捕捉到错误则通知UI线程
//            MainActivity.handler.sendEmptyMessage(COPY_FALSE);
        }
    }

    // 生成文件
    public boolean isneed_CopyFile(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
}