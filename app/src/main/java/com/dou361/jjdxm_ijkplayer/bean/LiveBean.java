package com.dou361.jjdxm_ijkplayer.bean;

/**
 Created by longwu on 2016/12/03.
 */
public class LiveBean {

    /**
     * nickname : 🐈这只野喵有毒🕳
     * livestarttime : 1473031828564
     * liveStream : http://pull.kktv8.com/livekktv/109204379.flv
     * portrait : /portrait/20160814/10/109204379_588711.jpg!256
     */

    private String nickname;
    private long livestarttime;
    private String liveStream;
    private String portrait;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getLivestarttime() {
        return livestarttime;
    }

    public void setLivestarttime(long livestarttime) {
        this.livestarttime = livestarttime;
    }

    public String getLiveStream() {
        return liveStream;
    }

    public void setLiveStream(String liveStream) {
        this.liveStream = liveStream;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
