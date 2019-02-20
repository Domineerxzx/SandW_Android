package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.SelectAllHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class SelectAllManager implements ServiceConnection {

    private Context context;
    private SelectAllHandler selectAllHandler;

    public SelectAllManager(Context context, SelectAllHandler selectAllHandler) {
        this.context = context;
        this.selectAllHandler = selectAllHandler;
        selectAllHandler.setSelectAllManager(this);
    }

    public void selectAll(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.selectAll(context,selectAllHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
