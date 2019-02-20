package com.triplebro.aran.sandw.properties;

public class AppProperties {
    //TODO 存放服务器访问的地址，软件内需要的常量

    public static final int UPDATE_USER_INFO_WHAT_OUTSIDE = 1;
    public static final int UPDATE_USER_INFO_WHAT_INSIDE = 2;

    //TODO 登录地址
    public static final String SERVER_ADDRESS_OF_LOGIN = "http://120.25.96.141:8080/login/loginprove";
    //TODO 注册地址
    public static final String SERVER_ADDRESS_OF_REGISTER = "http://120.25.96.141:8080//login/userRegister";
    //TODO 获取用户信息地址
    public static final String SERVER_ADDRESS_OF_SHOW_USER_INFO = "http://120.25.96.141:8080/login/userInit";
    //TODO 修改用户信息地址
    public static final String SERVER_ADDRESS_OF_CHANGE_USER_INFO = "http://120.25.96.141:8080/user/changeuserinfo";
    //TODO 修改密码地址
    public static final String SERVER_ADDRESS_OF_CHANGE_PASSWORD = "http://120.25.96.141:8080/login/changepasswd";
    //TODO 获取地址列表
    public static final String SERVER_ADDRESS_OF_SHOW_ADDRESS = "http://120.25.96.141:8080/address/list";
    //TODO 获取地址详细信息
    public static final String SERVER_ADDRESS_OF_SHOW_ADDRESS_MORE = "http://120.25.96.141:8080/address/info";
    //TODO 新增地址
    public static final String SERVER_ADDRESS_OF_ADD_ADDRESS = "http://120.25.96.141:8080/address/save";
    //TODO 修改地址
    public static final String SERVER_ADDRESS_OF_CHANGE_ADDRESS = "http://120.25.96.141:8080/address/save";
    //TODO 删除地址
    public static final String SERVER_ADDRESS_OF_DELETE_ADDRESS = "http://120.25.96.141:8080/address/dele";
    //TODO 获取商品类型
    public static final String SERVER_ADDRESS_OF_GET_TYPE = "http://120.25.96.141:8080/search/rangelist";
    //TODO 获取品牌
    public static final String SERVER_ADDRESS_OF_GET_BRAND = "http://120.25.96.141:8080/search/brandlist";
    //TODO 获取四格推荐
    public static final String SERVER_ADDRESS_OF_GET_GOODS_INFO = "http://120.25.96.141:8080/commodity/recommendation";
    //TODO 获取详细商品信息
    public static final String SERVER_ADDRESS_OF_GET_GOOD_INFO = "http://120.25.96.141:8080/commodity/commodityinfo";
    //TODO 获取选购全部信息
    public static final String SERVER_ADDRESS_OF_SELECT_ALL_TYPE = "http://120.25.96.141:8080/search/rangesearch";
    //TODO 获取搜索结果
    public static final String SERVER_ADDRESS_OF_FIND = "http://120.25.96.141:8080/search/description";
    //TODO 获取购物车信息
    public static final String SERVER_ADDRESS_OF_SHOW_SHOP_BAG = "http://120.25.96.141:8080/shopping/shoppinglist";
    //TODO 删除购物车信息
    public static final String SERVER_ADDRESS_OF_DELETE_SHOP_BAG = "http://120.25.96.141:8080/shopping/deleshopping";
    //TODO 添加购物车信息
    public static final String SERVER_ADDRESS_OF_ADD_SHOP_BAG = "http://120.25.96.141:8080/shopping/joinshopping";
    //TODO 获取品牌商品信息
    public static final String SERVER_ADDRESS_OF_SELECT_ALL_BRAND = "http://120.25.96.141:8080/search/brandSearch";
    //TODO 获取心愿单信息
    public static final String SERVER_ADDRESS_OF_GET_LOVES_LIST = "http://120.25.96.141:8080/shopping/collectshow";
    //TODO 添加心愿单信息
    public static final String SERVER_ADDRESS_OF_ADD_LOVES_LIST = "http://120.25.96.141:8080/shopping/collectinsert";
    //TODO 获取搜索推荐商品信息
    public static final String SERVER_ADDRESS_OF_SEARCH_GET_GOODS = "http://120.25.96.141:8080/recommend/tencommodity";
    //TODO 获取搜索推荐品牌信息
    public static final String SERVER_ADDRESS_OF_SEARCH_GET_BRANDS = "http://120.25.96.141:8080/recommend/fivebrand";
    //TODO 国家及地区
    public static final String[] COUNTRY_OR_AREA = {"中国内地"};
    //TODO 省
    public static final String[] PROVINCE = {"上海市","云南省","内蒙古自治区","北京市","吉林省","四川省","天津市",
            "宁夏回族自治区","安徽省","山东省","山西省","广东省","广西壮族自治区","新疆维吾尔自治区","江苏省",
            "江西省","河北省","河南省","浙江省","海南省","湖北省","湖南省","甘肃省","福建省","西藏自治区","贵州省","辽宁省",
            "重庆市","陕西省","青海省","黑龙江省"};

    public static final int GET_GOODS_INFO = 1;
    public static final int SEARCH_FIND = 1;
    public static final int SEARCH_GET_GOODS = 2;
    public static final int SHOP_BAG_SHOW = 1;
    public static final int SHOP_BAG_DELETE = 2;
    public static final int SHOP_BAG_ADD = 3;
    public static final int SHOP_BAG_EMPTY = 0;
    public static final int LOVES_LIST_SHOW = 1;
    public static final int LOVES_LIST_ADD = 2;
    public static final int LOVES_LIST_EMPTY = 3;
    public static final String LOVES_TABLE = "loves";
}
