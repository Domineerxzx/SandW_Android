package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.TypeOnClickHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class TypeOnClickManager implements ServiceConnection {
    private Context context;
    private TypeOnClickHandler typeOnClickHandler;
    private String typeName;

    public TypeOnClickManager(Context context, TypeOnClickHandler typeOnClickHandler, String typeName) {
        this.context = context;
        this.typeOnClickHandler = typeOnClickHandler;
        this.typeName = typeName;

    }

    public void startSelectAllActivity(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        typeOnClickHandler.setTypeOnClickManager(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        final NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.startSelectAllActivity(context,typeName,typeOnClickHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
