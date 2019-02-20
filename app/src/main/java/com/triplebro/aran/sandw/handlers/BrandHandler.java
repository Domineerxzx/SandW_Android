package com.triplebro.aran.sandw.handlers;

import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.activities.MainActivity;
import com.triplebro.aran.sandw.fragmentReact.ReactBrandFragment;
import com.triplebro.aran.sandw.managers.BrandManager;

public class BrandHandler extends Handler {
    private Context context;
    private BrandManager brandManager;
    private Fragment fragment;

    public BrandHandler(Context context,Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public BrandManager getBrandManager() {
        return brandManager;
    }

    public void setBrandManager(BrandManager brandManager) {
        this.brandManager = brandManager;
    }

    @Override
    public void handleMessage(Message msg) {
        String goodsInfo = (String) msg.obj;
        ((ReactBrandFragment)fragment).getReactPackage().setData(goodsInfo);
        ((MainActivity)context).getFragmentManager().beginTransaction().add(R.id.fl_brand_content,fragment).commit();
    }
}
