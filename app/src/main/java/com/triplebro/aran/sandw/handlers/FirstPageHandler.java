package com.triplebro.aran.sandw.handlers;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.activities.MainActivity;
import com.triplebro.aran.sandw.fragmentReact.ReactFirstPageFragment;
import com.triplebro.aran.sandw.managers.FirstPageManager;
import com.triplebro.aran.sandw.modules.AransPackage;
import com.triplebro.aran.sandw.properties.AppProperties;

public class FirstPageHandler extends Handler{

    private Context context;
    private Fragment fragment;
    private FirstPageManager firstPageManager;

    public FirstPageHandler(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    public FirstPageManager getFirstPageManager() {
        return firstPageManager;
    }

    public void setFirstPageManager(FirstPageManager firstPageManager) {
        this.firstPageManager = firstPageManager;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case AppProperties.GET_GOODS_INFO:
                String goodsInfo = (String) msg.obj;
                AransPackage aransPackage = ((ReactFirstPageFragment)fragment).getAransPackage();
                aransPackage.setData(goodsInfo);
                FragmentTransaction fragmentTransaction = ((MainActivity) context).getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fl_content,fragment);
                fragmentTransaction.commit();
                context.unbindService(firstPageManager);
                break;
        }
    }
}
