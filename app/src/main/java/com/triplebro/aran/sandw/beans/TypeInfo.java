package com.triplebro.aran.sandw.beans;

import java.util.List;

public class TypeInfo {

    private List<BigRangeListBean> bigRangeList;

    public List<BigRangeListBean> getBigRangeList() {
        return bigRangeList;
    }

    public void setBigRangeList(List<BigRangeListBean> bigRangeList) {
        this.bigRangeList = bigRangeList;
    }

    public static class BigRangeListBean {
        /**
         * classValue : [{"bigRangeName":"短袖","itemRangeList":["T恤"]},{"bigRangeName":"鞋","itemRangeList":["高跟鞋"]}]
         * className : Man
         */

        private String className;
        private List<ClassValueBean> classValue;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public List<ClassValueBean> getClassValue() {
            return classValue;
        }

        public void setClassValue(List<ClassValueBean> classValue) {
            this.classValue = classValue;
        }

        public static class ClassValueBean {
            /**
             * bigRangeName : 短袖
             * itemRangeList : ["T恤"]
             */

            private String bigRangeName;
            private List<String> itemRangeList;

            public String getBigRangeName() {
                return bigRangeName;
            }

            public void setBigRangeName(String bigRangeName) {
                this.bigRangeName = bigRangeName;
            }

            public List<String> getItemRangeList() {
                return itemRangeList;
            }

            public void setItemRangeList(List<String> itemRangeList) {
                this.itemRangeList = itemRangeList;
            }
        }
    }
}
