package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.ShowAddressInfoHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class ShowAddressInfoManager implements ServiceConnection {

    private Context context;
    private ShowAddressInfoHandler showAddressInfoHandler;
    private String address_id;

    public ShowAddressInfoManager(Context context, ShowAddressInfoHandler showAddressInfoHandler, String address_id) {
        this.context = context;
        this.showAddressInfoHandler = showAddressInfoHandler;
        this.address_id = address_id;
    }

    public void showAddressInfo(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.showAddressInfo(context,showAddressInfoHandler,address_id);

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
