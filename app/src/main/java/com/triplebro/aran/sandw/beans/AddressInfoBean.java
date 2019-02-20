package com.triplebro.aran.sandw.beans;

import java.util.List;

public class AddressInfoBean{


    /**
     * ListNull : false
     * addressBookList : [{"address":"ddd","city":"ccc","country":"中国内地","deleSign":0,"id":56,"name":"aaa","phone":"18840919546","postCode":"116044","province":"辽宁省","surName":"bb","userNum":"SandW000051"},{"address":"a","city":"a","country":"中国内地","deleSign":0,"id":57,"name":"a","phone":"a","postCode":"a","province":"辽宁省","surName":"a","userNum":"SandW000051"}]
     */

    private boolean ListNull;
    private List<AddressBookListBean> addressBookList;

    public boolean isListNull() {
        return ListNull;
    }

    public void setListNull(boolean ListNull) {
        this.ListNull = ListNull;
    }

    public List<AddressBookListBean> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(List<AddressBookListBean> addressBookList) {
        this.addressBookList = addressBookList;
    }

    public static class AddressBookListBean {
        /**
         * address : ddd
         * city : ccc
         * country : 中国内地
         * deleSign : 0
         * id : 56
         * name : aaa
         * phone : 18840919546
         * postCode : 116044
         * province : 辽宁省
         * surName : bb
         * userNum : SandW000051
         */

        private String address;
        private String city;
        private String country;
        private int deleSign;
        private int id;
        private String name;
        private String phone;
        private String postCode;
        private String province;
        private String surName;
        private String userNum;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getDeleSign() {
            return deleSign;
        }

        public void setDeleSign(int deleSign) {
            this.deleSign = deleSign;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }

        public String getUserNum() {
            return userNum;
        }

        public void setUserNum(String userNum) {
            this.userNum = userNum;
        }
    }
}
