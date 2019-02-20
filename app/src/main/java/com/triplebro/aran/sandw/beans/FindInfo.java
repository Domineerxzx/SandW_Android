package com.triplebro.aran.sandw.beans;

import java.util.List;

public class FindInfo {

    private List<RangeSearchBean> rangeSearch;

    public List<RangeSearchBean> getRangeSearch() {
        return rangeSearch;
    }

    public void setRangeSearch(List<RangeSearchBean> rangeSearch) {
        this.rangeSearch = rangeSearch;
    }

    public static class RangeSearchBean {
        /**
         * brandName : Champion
         * money : 207.84
         * id : 9
         * photoDoc : http://aaa
         */

        private String brandName;
        private String money;
        private int id;
        private String photoDoc;

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhotoDoc() {
            return photoDoc;
        }

        public void setPhotoDoc(String photoDoc) {
            this.photoDoc = photoDoc;
        }
    }
}
