package com.huayou.acc.acc.bean;

/**
 * Created by alu on 2017/5/10.
 */

public class User {

    /**
     * code : 200
     * info : SUCCESS
     * data : {"session_id":"73df4368feeef364527ff6cd2256511b","open_id":"","name":"13540356101","head_photo":"","background_url":"","phone":"13540356101","email":"","sex":0,"province":"","city":"","identity":0,"sign":"","detail":"","level":1,"card_id":"","car_num":"","captcha":""}
     */

    private int code;
    private String info;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * session_id : 73df4368feeef364527ff6cd2256511b
         * open_id :
         * name : 13540356101
         * head_photo :
         * background_url :
         * phone : 13540356101
         * email :
         * sex : 0
         * province :
         * city :
         * identity : 0
         * sign :
         * detail :
         * level : 1
         * card_id :
         * car_num :
         * captcha :
         */

        private String session_id;
        private String open_id;
        private String name;
        private String head_photo;
        private String background_url;
        private String phone;
        private String email;
        private int sex;
        private String province;
        private String city;
        private int identity;
        private String sign;
        private String detail;
        private int level;
        private String card_id;
        private String car_num;
        private String captcha;

        @Override
        public String toString() {
            return "DataBean{" +
                    "session_id='" + session_id + '\'' +
                    ", open_id='" + open_id + '\'' +
                    ", name='" + name + '\'' +
                    ", head_photo='" + head_photo + '\'' +
                    ", background_url='" + background_url + '\'' +
                    ", phone='" + phone + '\'' +
                    ", email='" + email + '\'' +
                    ", sex=" + sex +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", identity=" + identity +
                    ", sign='" + sign + '\'' +
                    ", detail='" + detail + '\'' +
                    ", level=" + level +
                    ", card_id='" + card_id + '\'' +
                    ", car_num='" + car_num + '\'' +
                    ", captcha='" + captcha + '\'' +
                    '}';
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHead_photo() {
            return head_photo;
        }

        public void setHead_photo(String head_photo) {
            this.head_photo = head_photo;
        }

        public String getBackground_url() {
            return background_url;
        }

        public void setBackground_url(String background_url) {
            this.background_url = background_url;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getCar_num() {
            return car_num;
        }

        public void setCar_num(String car_num) {
            this.car_num = car_num;
        }

        public String getCaptcha() {
            return captcha;
        }

        public void setCaptcha(String captcha) {
            this.captcha = captcha;
        }
    }
}
