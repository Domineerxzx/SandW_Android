package com.triplebro.aran.sandw.beans;

import java.util.List;

public class ShopBagInfo {

    private List<ShoppingListBean> shoppingList;

    public List<ShoppingListBean> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingListBean> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public static class ShoppingListBean {
        /**
         * brandName : Champion
         * photo_doc : http://aaa
         * money : 207.84
         * size : M
         * count : 2
         * stockNum : 0
         * commodityId : 9
         */

        private String brandName;
        private String photo_doc;
        private String money;
        private String size;
        private int count;
        private int stockNum;
        private int commodityId;

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getPhoto_doc() {
            return photo_doc;
        }

        public void setPhoto_doc(String photo_doc) {
            this.photo_doc = photo_doc;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStockNum() {
            return stockNum;
        }

        public void setStockNum(int stockNum) {
            this.stockNum = stockNum;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }
    }
}
