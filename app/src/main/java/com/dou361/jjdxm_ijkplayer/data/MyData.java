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

//    央视 CCTV-1,http://117.144.248.49/HDcctv1.m3u8?authCode=07110409322147352675&stbId=006001FF0018120000060019F0D49567&Contentid=8813322615956633846&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-2,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=8878432746825677699&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-3,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=4890526699577299046&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-4,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=4719673858948437836&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-5,http://117.144.248.49/cctv5.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=4867251683694877276&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-6,http://117.144.248.49/cctv6.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=6994512760913257617&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-7,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=7823098201377312515&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-8,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=8967744667154419073&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-9,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=6350727550077620987&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-10,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=7284663355496915828&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-11,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=8983629816140945539&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-12,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=6865782700657580918&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-13,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=003801FF001381001513BC20BA6E48D6&Contentid=8593685226043367730&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-14,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=006001FF0018120000060019F0D496A1&Contentid=6113730085955692619&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
//    央视 CCTV-15,http://117.144.248.49/z.m3u8?authCode=07110409322147352675&stbId=006001FF0018120000060019F0D496A1&Contentid=5565738654152032083&mos=jbjhhzstsl&livemode=1&channel-id=wasusyt
}
