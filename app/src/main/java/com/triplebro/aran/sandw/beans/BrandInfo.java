package com.triplebro.aran.sandw.beans;

import java.util.List;

public class BrandInfo {

    private List<BrandListBean> brandList;

    public List<BrandListBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListBean> brandList) {
        this.brandList = brandList;
    }

    public static class BrandListBean {
        /**
         * classValue : [{"charClassName":"C","charClassValue":["Champion"]},{"charClassName":"S","charClassValue":["SAM EDELMAN"]}]
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
             * charClassName : C
             * charClassValue : ["Champion"]
             */

            private String charClassName;
            private List<String> charClassValue;

            public String getCharClassName() {
                return charClassName;
            }

            public void setCharClassName(String charClassName) {
                this.charClassName = charClassName;
            }

            public List<String> getCharClassValue() {
                return charClassValue;
            }

            public void setCharClassValue(List<String> charClassValue) {
                this.charClassValue = charClassValue;
            }
        }
    }
}
