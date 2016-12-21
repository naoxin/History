package com.result.view;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 日期工具类
 */
public class DateUtil {
    public static int num=0;
    public static String getDateStr(String day,int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年M月d日");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() +(long)Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }
    public static String getNowDateStr() {
        Date newDate2 = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }



    public static String getMonth(String day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年M月d日");
        Date nowDate = null;
        String month="";
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
//        Date newDate2 = new Date(nowDate.getTime() );
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M");
        if(nowDate==null){
            month = simpleDateFormat.format(System.currentTimeMillis());
        }else{
            month = simpleDateFormat.format(nowDate);
        }


        return month;
    }
    public static String getMonthDate(String day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年M月d日");
        Date nowDate = null;
        String date;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
//        Date newDate2 = new Date(nowDate.getTime() );

        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("d");
        if(nowDate==null){
            date = simpleDateFormat.format(System.currentTimeMillis());
        }else{
            date = simpleDateFormat.format(nowDate);
        }

        return date;
    }

}
