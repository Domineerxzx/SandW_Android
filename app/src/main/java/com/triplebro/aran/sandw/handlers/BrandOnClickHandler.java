package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.SelectAllActivity;
import com.triplebro.aran.sandw.managers.BrandOnClickManager;

public class BrandOnClickHandler extends Handler {
    private Context context;
    private BrandOnClickManager brandOnClickManager;

    public BrandOnClickHandler(Context context) {
        this.context = context;
    }

    public void setBrandOnClickManager(BrandOnClickManager brandOnClickManager) {
        this.brandOnClickManager = brandOnClickManager;
    }

    @Override
    public void handleMessage(Message msg) {
        String selectAllBrand = (String) msg.obj;
        Intent intent = new Intent(context, SelectAllActivity.class);
        intent.putExtra("data",selectAllBrand);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        context.unbindService(brandOnClickManager);
    }
}
