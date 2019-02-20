package com.triplebro.aran.sandw.beans;

import java.util.List;

public class TenCommodityInfo {


    private List<TencommodityBean> tencommodity;

    public List<TencommodityBean> getTencommodity() {
        return tencommodity;
    }

    public void setTencommodity(List<TencommodityBean> tencommodity) {
        this.tencommodity = tencommodity;
    }

    public static class TencommodityBean {
        /**
         * brandName : Champion
         * photoDoc : http://thethreestooges.cn/sandw
         */

        private String brandName;
        private String photoDoc;

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getPhotoDoc() {
            return photoDoc;
        }

        public void setPhotoDoc(String photoDoc) {
            this.photoDoc = photoDoc;
        }
    }
}
