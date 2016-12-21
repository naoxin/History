package com.result.bean;

/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * Eventbus传值日历传值bean
 */

public class FirstEvent_Rili {
   private int year;
    private int day;

    public FirstEvent_Rili(int year, int day) {
        this.year = year;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
