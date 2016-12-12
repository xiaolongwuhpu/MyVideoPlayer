package com.longwu.ijkplayer.bean;

import java.util.List;

/**
 * Created by 龙五 on 2016/12/11.
 */
public class Root {
    private String reason;

    private List<Result> result;

    private int error_code;

    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
    public void setResult(List<Result> result){
        this.result = result;
    }
    public List<Result> getResult(){
        return this.result;
    }
    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }


}
