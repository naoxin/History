package com.result.bean;

/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * Eventbus传值bean
 */

public class GrilFirstEvent {
    private String mMsg;
    public GrilFirstEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
