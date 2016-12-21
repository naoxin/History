package com.result.bean;

import java.util.List;
/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 历史页面bean
 */

public class Date {


    /**
     * reason : success
     * result : [{"day":"11/1","date":"1120年11月01日","title":"北宋江南方腊起义","e_id":"12308"},{"day":"11/1","date":"1755年11月01日","title":"里斯本地震","e_id":"12309"},{"day":"11/1","date":"1902年11月01日","title":"挪威作家格里格诞生","e_id":"12310"},{"day":"11/1","date":"1907年11月01日","title":"电影导演吴永刚诞生","e_id":"12311"},{"day":"11/1","date":"1911年11月01日","title":"袁世凯出任清朝内阁总理大臣","e_id":"12312"},{"day":"11/1","date":"1911年11月01日","title":"路易斯·雪佛兰成立汽车公司","e_id":"12313"},{"day":"11/1","date":"1911年11月01日","title":"上海光复，陈其美任沪军都督","e_id":"12314"},{"day":"11/1","date":"1912年11月01日","title":"北京召开临时工商会议","e_id":"12315"},{"day":"11/1","date":"1919年11月01日","title":"美国历史上第一次工人运动高潮","e_id":"12316"},{"day":"11/1","date":"1926年11月01日","title":"北伐军攻陷南昌孙传芳主力被歼","e_id":"12317"},{"day":"11/1","date":"1927年11月01日","title":"宋庆龄等成立国民党临时行动委员会","e_id":"12318"},{"day":"11/1","date":"1928年11月01日","title":"中央银行成立","e_id":"12319"},{"day":"11/1","date":"1935年11月01日","title":"汪精卫南京遇刺","e_id":"12320"},{"day":"11/1","date":"1937年11月01日","title":"国立长沙临时大学开学","e_id":"12321"},{"day":"11/1","date":"1939年11月01日","title":"洛克菲勒中心开张营业","e_id":"12322"},{"day":"11/1","date":"1948年11月01日","title":"解放军发布《惩处战争罪犯命令》","e_id":"12323"},{"day":"11/1","date":"1948年11月01日","title":"中共中央军委颁发整编命令","e_id":"12324"},{"day":"11/1","date":"1949年11月01日","title":"\u201c西南战役\u201d开始","e_id":"12325"},{"day":"11/1","date":"1949年11月01日","title":"中国科学院成立","e_id":"12326"},{"day":"11/1","date":"1952年11月01日","title":"美国第一颗氢弹爆炸成功","e_id":"12327"},{"day":"11/1","date":"1954年11月01日","title":"阿尔及利亚民族解放战争爆发","e_id":"12328"},{"day":"11/1","date":"1955年11月01日","title":"南非首次选举地方政府","e_id":"12329"},{"day":"11/1","date":"1955年11月01日","title":"美国著作家、教育家戴尔·卡内基逝世","e_id":"12330"},{"day":"11/1","date":"1958年11月01日","title":"我国与摩洛哥建交","e_id":"12331"},{"day":"11/1","date":"1958年11月01日","title":"卢蒙巴在刚果被捕","e_id":"12332"},{"day":"11/1","date":"1963年11月01日","title":"我军击落台湾U-2间谍飞机","e_id":"12333"},{"day":"11/1","date":"1984年11月01日","title":"邓小平提出裁军100万","e_id":"12334"},{"day":"11/1","date":"1984年11月01日","title":"我国与阿联酋建交","e_id":"12335"},{"day":"11/1","date":"1984年11月01日","title":"拉吉夫·甘地出任印度总理","e_id":"12336"},{"day":"11/1","date":"1986年11月01日","title":"瑞士桑多兹化工厂爆炸","e_id":"12337"},{"day":"11/1","date":"1989年11月01日","title":"全球首个网站诞生","e_id":"12338"},{"day":"11/1","date":"1991年11月01日","title":"中国发表人权状况白皮书","e_id":"12339"},{"day":"11/1","date":"1991年11月01日","title":"唐长乐公主墓对外开放","e_id":"12340"},{"day":"11/1","date":"1991年11月01日","title":"卢刚事件","e_id":"12341"},{"day":"11/1","date":"1993年11月01日","title":"\u201c哥伦比亚\u201d号创太空飞行纪录","e_id":"12342"},{"day":"11/1","date":"1993年11月01日","title":"美国科学家提出恐龙灭绝新说","e_id":"12343"},{"day":"11/1","date":"2008年11月01日","title":"\u201c比特币\u201d面世","e_id":"12344"},{"day":"11/1","date":"2008年11月01日","title":"中国现代遗传学奠基人谈家桢逝世","e_id":"12345"},{"day":"11/1","date":"2011年11月01日","title":"中国新疆伊犁发生6.0级地震","e_id":"12346"},{"day":"11/1","date":"2015年11月01日","title":"\u201c私募一哥\u201d徐翔被抓","e_id":"12347"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * day : 11/1
         * date : 1120年11月01日
         * title : 北宋江南方腊起义
         * e_id : 12308
         */

        private String day;
        private String date;
        private String title;
        private String e_id;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }
    }
}
