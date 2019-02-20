package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.GoodInfoActivity;
import com.triplebro.aran.sandw.activities.MainActivity;
import com.triplebro.aran.sandw.fragmentReact.ReactFirstPageFragment;
import com.triplebro.aran.sandw.managers.GoodInfoManager;
import com.triplebro.aran.sandw.modules.AransModules;
import com.triplebro.aran.sandw.modules.AransPackage;

public class GoodInfoHandler extends Handler {

    private Context context;
    private GoodInfoManager goodInfoManager;

    public GoodInfoHandler(Context context) {
        this.context = context;
    }

    public void setGoodInfoManager(GoodInfoManager goodInfoManager) {
        this.goodInfoManager = goodInfoManager;
    }

    @Override
    public void handleMessage(Message msg) {
        String goodInfo = (String) msg.obj;
        context.unbindService(goodInfoManager);
        Intent intent = new Intent(context, GoodInfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data",goodInfo);
        context.startActivity(intent);
    }
}
