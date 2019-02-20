package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.beans.AddAddressInfoBean;
import com.triplebro.aran.sandw.handlers.AddAddressHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class AddAddressManager implements ServiceConnection {

    private Context context;
    private AddAddressHandler addAddressHandler;
    private AddAddressInfoBean addAddressInfoBean;
    private String session;

    public AddAddressManager(Context context, AddAddressHandler addAddressHandler, AddAddressInfoBean addAddressInfoBean, String session) {
        this.context = context;
        this.addAddressHandler = addAddressHandler;
        this.addAddressInfoBean = addAddressInfoBean;
        this.session = session;
    }

    public void addAddress(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.addAddress(context,addAddressHandler,addAddressInfoBean,session);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
