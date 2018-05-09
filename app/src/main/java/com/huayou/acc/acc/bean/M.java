package com.huayou.acc.acc.bean;

import java.util.List;

/**
 * Created by alu on 2017/5/27.
 */

public class M {

    /**
     * code : 200
     * info : SUCCESS
     * head : {"title":"新津加油站","addr":"新津。。。","position":"103.807814,30.384596","phone":"90909801"}
     * tickets : [{"id":6,"pay_methods":[0,1,2],"status":0,"orgn_price":9.8,"price":9.8,"discount":0,"start_time":"2017-05-24 14:34:00","end_time":"2018-05-24 14:34:00","name":"#98","unit":"升","detail":""},{"id":7,"pay_methods":[0,1,2],"status":0,"orgn_price":9.6,"price":9.6,"discount":1,"start_time":"2017-05-24 14:35:00","end_time":"2018-05-24 14:35:00","name":"9.6","unit":"升","detail":""},{"id":8,"pay_methods":[0,1,2],"status":0,"orgn_price":8.9,"price":8.9,"discount":0,"start_time":"2017-05-24 14:35:00","end_time":"2018-05-24 14:35:00","name":"8.9","unit":"立方","detail":""}]
     */

    private int code;
    private String info;
    private HeadBean head;
    private List<TicketsBean> tickets;

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

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public List<TicketsBean> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketsBean> tickets) {
        this.tickets = tickets;
    }

    public static class HeadBean {
        /**
         * title : 新津加油站
         * addr : 新津。。。
         * position : 103.807814,30.384596
         * phone : 90909801
         */

        private String title;
        private String addr;
        private String position;
        private String phone;

        @Override
        public String toString() {
            return "HeadBean{" +
                    "title='" + title + '\'' +
                    ", addr='" + addr + '\'' +
                    ", position='" + position + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class TicketsBean {
        /**
         * id : 6
         * pay_methods : [0,1,2]
         * status : 0
         * orgn_price : 9.8
         * price : 9.8
         * discount : 0.0
         * start_time : 2017-05-24 14:34:00
         * end_time : 2018-05-24 14:34:00
         * name : #98
         * unit : 升
         * detail :
         */

        private int id;
        private int status;
        private double orgn_price;
        private double price;
        private double discount;
        private String start_time;
        private String end_time;
        private String name;
        private String unit;
        private String detail;
        private List<Integer> pay_methods;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getOrgn_price() {
            return orgn_price;
        }

        public void setOrgn_price(double orgn_price) {
            this.orgn_price = orgn_price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public List<Integer> getPay_methods() {
            return pay_methods;
        }

        public void setPay_methods(List<Integer> pay_methods) {
            this.pay_methods = pay_methods;
        }
    }
}
