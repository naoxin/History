package com.result.bean;

/**
 * autour: 李延
 * date: 2016/12/21 20:33
 * update: 2016/12/21
 * Eventbus传值历史id传值bean
 */

public class FirstEvent {
   private String e_id;

    public FirstEvent(String e_id) {
        this.e_id = e_id;
    }

    public String getE_id() {
        return e_id;
    }
}
