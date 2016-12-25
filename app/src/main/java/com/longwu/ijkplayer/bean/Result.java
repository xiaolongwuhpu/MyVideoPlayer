package com.longwu.ijkplayer.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by 龙五 on 2016/12/11.
 */
public class Result {

    private String id;
    private String title;
    private String des;
    private List<String> list;
    @JSONField(name="id")
    public void setId(String id) {
        this.id = id;
    }
    @JSONField(name="id")
    public String getId() {
        return id;
    }
    @JSONField(name="title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JSONField(name="title")
    public String getTitle() {
        return title;
    }
    @JSONField(name="des")
    public void setDes(String des) {
        this.des = des;
    }
    @JSONField(name="des")
    public String getDes() {
        return des;
    }
    @JSONField(name="list")
    public void setList(List<String> list) {
        this.list = list;
    }
    @JSONField(name="list")
    public List<String> getList() {
        return list;
    }

}


//    private String id;
//
//    private String name;
//
//    private String fid;
//    @JSONField(name="id")
//    public void setId(String id){
//        this.id = id;
//    }
//    @JSONField(name="id")
//    public String getId(){
//        return this.id;
//    }
//
//    @JSONField(name="name")
//    public void setName(String name){
//        this.name = name;
//    }
//    @JSONField(name="name")
//    public String getName(){
//        return this.name;
//    }
//
//    @JSONField(name="fid")
//    public void setFid(String fid){
//        this.fid = fid;
//    }
//    @JSONField(name="fid")
//    public String getFid(){
//        return this.fid;
//    }
//}
