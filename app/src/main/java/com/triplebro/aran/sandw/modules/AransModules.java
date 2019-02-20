package com.triplebro.aran.sandw.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.triplebro.aran.sandw.handlers.GoodInfoHandler;
import com.triplebro.aran.sandw.handlers.LovesHandler;
import com.triplebro.aran.sandw.handlers.SelectAllHandler;
import com.triplebro.aran.sandw.handlers.ShopBagHandler;
import com.triplebro.aran.sandw.managers.GoodInfoManager;
import com.triplebro.aran.sandw.managers.LovesManager;
import com.triplebro.aran.sandw.managers.SelectAllManager;
import com.triplebro.aran.sandw.managers.ShopBagManager;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Aran on 2018/6/11.
 * 那当自己都萎靡到
 * 无法被依靠的时候该如何振作？
 * <p>
 * 除过自己心中笃信的那一点不灭的光亮
 * 我觉得这世间再没有别的东西比它值得被如此依靠。
 */

public class AransModules extends ReactContextBaseJavaModule {
    public static String type;
    private Context mContext;
    private Object data;
    public static String title;
    public static String commodityId;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AransModules(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "AransModules";
    }

    //TODO 告知Android首页需要展示需要的标签
    @ReactMethod
    public void setTitle(String msg) {//传递标签
        title = msg;
    }

    //TODO 告知Android查看商品详情需要的商品Id
    @ReactMethod
    public void setCommodityId(String msg) {//传递ID
        commodityId = msg;
    }

    //TODO 告知Android选购全部需要的商品类别
    @ReactMethod
    public static void setType(String type) {
        AransModules.type = type;
    }

    //TODO Rn向Android传递信息，并且打印Log
    @ReactMethod
    public void SEND_LOG(String o) {
        System.out.println("======================这里是log==================" + o);
    }

    //TODO Rn调用获取四格推荐，在这之前必须调用setTitle()
    @ReactMethod
    public void getGoodsInfo(Callback callback) {
        callback.invoke(String.valueOf(data));
        data = null;
    }

    //TODO Rn调用获取商品详情，在这之前必须调用setCommodityId()
    @ReactMethod
    public void getGoodInfo(Callback callback) {
        callback.invoke(String.valueOf(data));
        data = null;
    }

    //TODO Rn调用获取选购全部，在这之前必须调用setType()
    @ReactMethod
    public void getSelectAll(Callback callback) {
        callback.invoke(String.valueOf(data));
        data = null;
    }

    //TODO Rn调用添加心愿单
    @ReactMethod
    public void getLovesList(Callback callback) {
        callback.invoke(String.valueOf(data));
        data = null;
    }

    //TODO Rn调用添加购物袋
    @ReactMethod
    public void addShopBag(String commodityId, String sizeName) {
        ShopBagHandler shopBagHandler = new ShopBagHandler(mContext);
        ShopBagManager shopBagManager = new ShopBagManager(mContext, shopBagHandler, mContext.getSharedPreferences("session", MODE_PRIVATE).getString("session", null), commodityId, sizeName);
        shopBagManager.addShopBag();
    }

    //TODO Rn调用添加心愿单
    @ReactMethod
    public void addLovesList(String commodityId) {
        LovesHandler lovesHandler = new LovesHandler(mContext);
        LovesManager lovesManager = new LovesManager(mContext, lovesHandler, mContext.getSharedPreferences("session", MODE_PRIVATE).getString("session", null), commodityId);
        lovesManager.addLovesList();
    }

    @ReactMethod
    public void TanToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //TODO Rn调用Android开启商品详情页
    @ReactMethod
    public void startGoodInfoActivity() {

        GoodInfoHandler goodInfoHandler = new GoodInfoHandler(mContext);
        GoodInfoManager goodInfoManager = new GoodInfoManager(mContext, goodInfoHandler);
        goodInfoManager.getGoodInfo();
    }

    //TODO Rn调用Android开启选购全部页
    @ReactMethod
    public void startSelectAllActivity() {
        SelectAllHandler selectAllHandler = new SelectAllHandler(mContext);
        SelectAllManager selectAllManager = new SelectAllManager(mContext, selectAllHandler);
        selectAllManager.selectAll();
        Toast.makeText(mContext, "跳转页面成功", Toast.LENGTH_SHORT).show();
    }

    //TODO 暂时不用
    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> params = new HashMap<>();
        params.put("aa", "hahaha");
        params.put("bb", "xixixi");
        return params;
    }
}
