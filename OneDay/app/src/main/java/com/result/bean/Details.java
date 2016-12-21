package com.result.bean;

import java.util.List;

/**
 * autour: 李延
 * date: 2016/12/21 20:34
 * update: 2016/12/21
 * 详情页面bean
 */

public class Details {


    /**
     * reason : success
     * result : [{"e_id":"12318","title":"宋庆龄等成立国民党临时行动委员会","content":"    在89年前的今天，1927年11月1日 (农历十月初八)，宋庆龄等成立国民党临时行动委员会。\r\n    1927年11月1日（距今89年），宋庆龄、邓演达、陈友仁在莫斯科以中国国民党临时行动委员会的名义发表《对中国及世界革命民众宣言》。\r\n    宣言说，中国现在己进入到一个新的局势。南北新旧军阀，地方土豪、绅士、买办对工农群众加紧其杀戮和剥削，形成新的黑暗反动时代。革命的民众，由希望解放的空想而进入于实行斗争，由依靠\u201c好政府\u201d盼望真命天子的残梦惊醒，在血泊中进行新的解放自己的工作。宣言说，民众之痛苦，根本原因，在帝国主义与封建军阀、地主豪绅及大部分与他们联合的资本家的共同勾结所构成的中国政治经济势力的统治。宣言宣告，本会之责任，在宣告南京政府的伪党部中央之罪过，以革命手段中止其受第二次大会委托之职权；并临时行使革命指导之机能。本会职权，到全国各省市代表大会成立日起，即行取消，以明责任。\r\n\r\n","picNo":"1","picUrl":[{"pic_title":"","id":1,"url":"http://images.juheapi.com/history/12318_1.jpg"}]}]
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
         * e_id : 12318
         * title : 宋庆龄等成立国民党临时行动委员会
         * content :     在89年前的今天，1927年11月1日 (农历十月初八)，宋庆龄等成立国民党临时行动委员会。
         1927年11月1日（距今89年），宋庆龄、邓演达、陈友仁在莫斯科以中国国民党临时行动委员会的名义发表《对中国及世界革命民众宣言》。
         宣言说，中国现在己进入到一个新的局势。南北新旧军阀，地方土豪、绅士、买办对工农群众加紧其杀戮和剥削，形成新的黑暗反动时代。革命的民众，由希望解放的空想而进入于实行斗争，由依靠“好政府”盼望真命天子的残梦惊醒，在血泊中进行新的解放自己的工作。宣言说，民众之痛苦，根本原因，在帝国主义与封建军阀、地主豪绅及大部分与他们联合的资本家的共同勾结所构成的中国政治经济势力的统治。宣言宣告，本会之责任，在宣告南京政府的伪党部中央之罪过，以革命手段中止其受第二次大会委托之职权；并临时行使革命指导之机能。本会职权，到全国各省市代表大会成立日起，即行取消，以明责任。


         * picNo : 1
         * picUrl : [{"pic_title":"","id":1,"url":"http://images.juheapi.com/history/12318_1.jpg"}]
         */

        private String e_id;
        private String title;
        private String content;
        private String picNo;
        private List<PicUrlBean> picUrl;

        public String getE_id() {
            return e_id;
        }

        public void setE_id(String e_id) {
            this.e_id = e_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicNo() {
            return picNo;
        }

        public void setPicNo(String picNo) {
            this.picNo = picNo;
        }

        public List<PicUrlBean> getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(List<PicUrlBean> picUrl) {
            this.picUrl = picUrl;
        }

        public static class PicUrlBean {
            /**
             * pic_title :
             * id : 1
             * url : http://images.juheapi.com/history/12318_1.jpg
             */

            private String pic_title;
            private int id;
            private String url;

            public String getPic_title() {
                return pic_title;
            }

            public void setPic_title(String pic_title) {
                this.pic_title = pic_title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
