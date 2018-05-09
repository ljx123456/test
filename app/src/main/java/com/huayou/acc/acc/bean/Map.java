package com.huayou.acc.acc.bean;

import java.util.List;

/**
 * Created by alu on 2017/4/27.
 */

public class Map {

    /**
     * code : 200
     * info : SUCCESS
     * list : [{"title":"成都高新区加油站","header":"/media/django-summernote/2017-04-05/cb13bd61-ec4f-4e22-b577-bddc2217db4f.jpeg","size":{"width":640,"height":429},"tags":"热卖 最新","city":"成都","address":"高新区孵化园德商国际c座","lng":104.072601,"lat":30.551902,"wap":"127.0.0.1/group/8","sid":"8a","tickets":[],"phone":"17708128119"},{"title":"新津加油站","header":"/media/django-summernote/2017-05-04/7cbf4350-c450-431b-93d2-e4def61466c0.jpeg","size":{"width":640,"height":429},"tags":"","city":"成都","address":"新津。。。","lng":30.384596,"lat":103.807814,"wap":"127.0.0.1/group/12","sid":"833b","tickets":[],"phone":"90909801"},{"title":"双流加油站","header":"/media/django-summernote/2017-05-03/c4d2745c-a794-497f-ada7-f500049537b2.png","size":{"width":1139,"height":662},"tags":"热卖  最近","city":"成都","address":"","lng":103.926603,"lat":30.571192,"wap":"127.0.0.1/group/11","sid":"8338","tickets":[],"phone":"400-222-888"},{"title":"成都青羊加油站","header":"/media/django-summernote/2017-04-27/ff238129-72f0-4508-866b-8c668b69d9e0.png","size":{"width":436,"height":463},"tags":"美食","city":"成都","address":"高新区青羊区","lng":104.029944,"lat":30.675482,"wap":"127.0.0.1/group/10","sid":"8339","tickets":[],"phone":"400-111-999"},{"title":"成都武侯加油站","header":"/media/django-summernote/2017-04-27/5cfb2673-7836-435c-bcc9-859ffe98b725.png","size":{"width":241,"height":424},"tags":"热卖  最近","city":"成都","address":"高新区孵化园","lng":104.022133,"lat":30.646762,"wap":"127.0.0.1/group/9","sid":"8b","tickets":[],"phone":"400-777-666"}]
     */

    private int code;
    private String info;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * title : 成都高新区加油站
         * header : /media/django-summernote/2017-04-05/cb13bd61-ec4f-4e22-b577-bddc2217db4f.jpeg
         * size : {"width":640,"height":429}
         * tags : 热卖 最新
         * city : 成都
         * address : 高新区孵化园德商国际c座
         * lng : 104.072601
         * lat : 30.551902
         * wap : 127.0.0.1/group/8
         * sid : 8a
         * tickets : []
         * phone : 17708128119
         */

        private String title;
        private String header;
        private SizeBean size;
        private String tags;
        private String city;
        private String address;
        private double lng;
        private double lat;
        private String wap;
        private String sid;
        private String phone;
        private List<?> tickets;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public SizeBean getSize() {
            return size;
        }

        public void setSize(SizeBean size) {
            this.size = size;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getWap() {
            return wap;
        }

        public void setWap(String wap) {
            this.wap = wap;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public List<?> getTickets() {
            return tickets;
        }

        public void setTickets(List<?> tickets) {
            this.tickets = tickets;
        }

        public static class SizeBean {
            /**
             * width : 640
             * height : 429
             */

            private int width;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
