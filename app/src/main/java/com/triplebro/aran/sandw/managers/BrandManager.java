package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.BrandHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class BrandManager implements ServiceConnection {
    private Context context;
    private BrandHandler brandHandler;

    public BrandManager(Context context, BrandHandler brandHandler) {
        this.context = context;
        this.brandHandler = brandHandler;
        brandHandler.setBrandManager(this);
    }
    public void getGoodsInfo(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.getGoodsInfo(context,brandHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
