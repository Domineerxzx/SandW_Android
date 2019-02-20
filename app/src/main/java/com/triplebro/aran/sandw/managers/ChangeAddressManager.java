package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.beans.ChangeAddressInfoBean;
import com.triplebro.aran.sandw.handlers.ChangeAddressHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class ChangeAddressManager implements ServiceConnection {

    private Context context;
    private ChangeAddressHandler changeAddressHandler;
    private ChangeAddressInfoBean changeAddressInfoBean;
    private String session;

    public ChangeAddressManager(Context context, ChangeAddressHandler changeAddressHandler, ChangeAddressInfoBean changeAddressInfoBean, String session) {
        this.context = context;
        this.changeAddressHandler = changeAddressHandler;
        this.changeAddressInfoBean = changeAddressInfoBean;
        this.session = session;
    }

    public void changeAddress() {
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.changeAddress(context,changeAddressHandler,changeAddressInfoBean,session);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
