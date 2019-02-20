package com.triplebro.aran.sandw.beans;

public class ShowAddressInfoBean {


    /**
     * addressBookInfo : {"address":"大连外国语大学","city":"大连市","country":"中国","deleSign":0,"id":46,"name":"钧元","phone":"15141433601","postCode":"117000","province":"辽宁省","surName":"罗","userNum":"SandW000050"}
     * option : false
     */

    private AddressBookInfoBean addressBookInfo;
    private boolean option;

    public AddressBookInfoBean getAddressBookInfo() {
        return addressBookInfo;
    }

    public void setAddressBookInfo(AddressBookInfoBean addressBookInfo) {
        this.addressBookInfo = addressBookInfo;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
    }

    public static class AddressBookInfoBean {
        /**
         * address : 大连外国语大学
         * city : 大连市
         * country : 中国
         * deleSign : 0
         * id : 46
         * name : 钧元
         * phone : 15141433601
         * postCode : 117000
         * province : 辽宁省
         * surName : 罗
         * userNum : SandW000050
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
