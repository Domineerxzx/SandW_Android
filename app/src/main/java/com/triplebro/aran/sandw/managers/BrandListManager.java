package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.BrandListHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class BrandListManager implements ServiceConnection {

    private Context context;
    private BrandListHandler brandListHandler;

    public BrandListManager(Context context, BrandListHandler brandListHandler) {
        this.context = context;
        this.brandListHandler = brandListHandler;
        brandListHandler.setBrandListManager(this);
    }

    public void getBrand(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.getBrand(context,brandListHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
