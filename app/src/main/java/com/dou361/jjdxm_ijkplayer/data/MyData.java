package com.dou361.jjdxm_ijkplayer.data;

import android.os.Environment;

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

    public static  MyData getinstance(){
        if (myData == null){
            myData = new MyData();
        }
        return myData;
    }

    public List<String> list1, list2;
    List<List<String>> list;

    public List<List<String>> getTxtFilelist(String strFilePath) {
        String path = strFilePath;
        StringBuilder builder = new StringBuilder();
        //打开文件
//        File file = new File(path);
        list1 =  new ArrayList<>();
        list2 =  new ArrayList<>();
        list = new ArrayList<>();
        File file = new File(Environment.getExternalStorageDirectory(),
                "abc.txt");
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
                        list1.add(line.substring(0,line.indexOf(",")).trim());
                        list2.add(line.substring(line.indexOf(",")+1).trim());
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

    String[] main_RecycleList = new String[]{"直播","影视"};
    public List<String> getMain_RecycleList(){
        return Arrays.asList(main_RecycleList);
    }
}
