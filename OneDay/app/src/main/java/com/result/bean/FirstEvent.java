package com.result.bean;

import java.util.List;

/**
 * 1.作用
 * 2.作者：李延
 * 3.时间：2016、11、24
 */

public class FirstEvent {
    private List<Date.ResultBean> mMsg;
    public FirstEvent(List<Date.ResultBean> msg) {
        // TODO Auto-generated constructor stub
        mMsg = msg;
    }
    public List<Date.ResultBean> getMsg(){
        return mMsg;
    }
}
