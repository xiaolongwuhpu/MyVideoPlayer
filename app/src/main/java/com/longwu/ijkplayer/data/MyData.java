package com.longwu.ijkplayer.data;

import android.os.Environment;

import com.longwu.ijkplayer.APP;
import com.longwu.ijkplayer.frgment.MyFragment4;
import com.longwu.ijkplayer.utlis.AssetsCopyTOSDcard;
import com.longwu.ijkplayer.utlis.SharedPreferencesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 龙五 on 2016/12/5.
 */
public class MyData {

    private static  MyData myData;
    public static String  rootdir = Environment.getExternalStorageDirectory()+"/longwuplayer";
    public static  MyData getinstance(){
        if (myData == null){
            myData = new MyData();
        }
        return myData;
    }

    public List<String> list1, list2;
    List<List<String>> list;
    public List<List<String>> getTxtFilelist(String strFilePath) {
        return getTxtFilelist(strFilePath,false);
    }
    public List<List<String>> getTxtFilelist(String strFilePath,boolean isCustomTxt) {
        String path = strFilePath;
        StringBuilder builder = new StringBuilder();
        File file;
        //打开文件
//        File file = new File(path);
        list1 =  new ArrayList<>();
        list2 =  new ArrayList<>();
        list = new ArrayList<>();
        if(isCustomTxt){

            AssetsCopyTOSDcard.makeRootDirectory(rootdir);
             file = new File(rootdir,strFilePath);

        }else{
             file = new File(APP.ctx.getExternalCacheDir(),strFilePath);
        }

        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream,"utf8");
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while ((line = buffreader.readLine()) != null) {
                        int index = line.indexOf(",");
                        if(index != -1 && !line.startsWith("//")){
                            list1.add(line.substring(0,index).trim());
                            list2.add(line.substring(index+1).trim());
                        }

                        builder.append(line + "\n");
                    }
                    instream.close();
                }

                list.add(list1);
                list.add(list2);
            } catch (java.io.FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
        return list;
    }
    public static String[] main_RecycleList = new String[]{"国内直播","国外直播","禁止直播","国外电影","国内电影","电视剧直播","体育篮球直播"};
    public static String[] main_RecycleList2 = new String[]{"国内直播","国外直播","禁止直播","国外电影","国内电影","电视剧直播","体育篮球直播","自定义视频"};
    public List<String> getMain_RecycleList(){
        return Arrays.asList(SharedPreferencesUtils.getBoolean(APP.ctx, MyFragment4.ISNEEDCUSTOM)?main_RecycleList2:main_RecycleList);
    }
    String[] Sever_RecycleList = new String[]{"新闻头条","周公解梦","历史的今天","笑口常开","电影票房","电视节目表","全国wifi","NBA赛事","全国公交查询","驾照题库","QQ号码测吉凶","身份证查询"};
    public List<String> getSever_RecycleList(){
        return Arrays.asList(Sever_RecycleList);
    }

}
