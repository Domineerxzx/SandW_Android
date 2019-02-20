package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.triplebro.aran.sandw.adapters.BrandContentAdapter;
import com.triplebro.aran.sandw.beans.BrandInfo;
import com.triplebro.aran.sandw.managers.BrandListManager;

import java.util.List;

public class BrandListHandler extends Handler {

    private Context context;
    private BrandListManager brandListManager;
    private  ListView lv_brand_list_content;
    private int sex;

    public BrandListHandler(Context context, ListView lv_brand_list_content, int sex) {
        this.context = context;
        this.lv_brand_list_content = lv_brand_list_content;
        this.sex = sex;
    }

    public BrandListManager getBrandListManager() {
        return brandListManager;
    }

    public void setBrandListManager(BrandListManager brandListManager) {
        this.brandListManager = brandListManager;
    }

    @Override
    public void handleMessage(Message msg) {
        BrandInfo brandInfo = (BrandInfo) msg.obj;
        List<BrandInfo.BrandListBean> brandList = brandInfo.getBrandList();
        List<BrandInfo.BrandListBean.ClassValueBean> classValue = brandList.get(sex).getClassValue();
        BrandContentAdapter brandContentAdapter = new BrandContentAdapter(context, classValue);
        lv_brand_list_content.setAdapter(brandContentAdapter);
    }
}
