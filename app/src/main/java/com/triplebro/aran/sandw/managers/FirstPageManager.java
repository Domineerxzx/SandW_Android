package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.FirstPageHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class FirstPageManager implements ServiceConnection {

    private Context context;
    private FirstPageHandler firstPageHandler;

    public FirstPageManager(Context context, FirstPageHandler firstPageHandler) {
        this.context = context;
        this.firstPageHandler = firstPageHandler;
        firstPageHandler.setFirstPageManager(this);
    }

    public void getGoodsInfo(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.getGoodsInfo(context,firstPageHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
