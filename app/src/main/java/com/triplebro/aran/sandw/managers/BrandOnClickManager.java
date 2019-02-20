package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.BrandOnClickHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class BrandOnClickManager implements ServiceConnection {

    private Context context;
    private String brandName;
    private BrandOnClickHandler brandOnClickHandler;

    public BrandOnClickManager(Context context, String brandName, BrandOnClickHandler brandOnClickHandler) {
        this.context = context;
        this.brandName = brandName;
        this.brandOnClickHandler = brandOnClickHandler;
        brandOnClickHandler.setBrandOnClickManager(this);
    }

    public void startSelectAllActivity(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.startSelectAllActivity(context,brandName,brandOnClickHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
