package com.longwu.ijkplayer.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by 龙五 on 2016/12/13.
 */
public class JsonRootBean {

    private String reason;

    private List<Result> result;

    private int errorCode;
    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
    @JSONField(name="result")
    public void setResult(List<Result> result) {
        this.result = result;
    }
    @JSONField(name="result")
    public List<Result> getResult() {
        return result;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode() {
        return errorCode;
    }

}
