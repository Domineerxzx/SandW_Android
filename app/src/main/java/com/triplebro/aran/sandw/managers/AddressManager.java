package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.AddressHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class AddressManager implements ServiceConnection {

    private Context context;
    private AddressHandler addressHandler;
    private String session;

    public AddressManager(Context context, AddressHandler addressHandler, String session) {
        this.context = context;
        this.addressHandler = addressHandler;
        this.session = session;
    }

    public void showAddress(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.showAddress(context,addressHandler,session);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
