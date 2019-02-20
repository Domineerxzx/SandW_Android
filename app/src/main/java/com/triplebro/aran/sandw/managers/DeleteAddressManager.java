package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.DeleteAddressHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class DeleteAddressManager implements ServiceConnection {
    private Context context;
    private DeleteAddressHandler deleteAddressHandler;
    private String session;
    private String address_id;

    public DeleteAddressManager(Context context, DeleteAddressHandler deleteAddressHandler, String session, String address_id) {
        this.context = context;
        this.deleteAddressHandler = deleteAddressHandler;
        this.session = session;
        this.address_id = address_id;
    }

    public void deleteAddress(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.deleteAddress(context,deleteAddressHandler,session,address_id);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
