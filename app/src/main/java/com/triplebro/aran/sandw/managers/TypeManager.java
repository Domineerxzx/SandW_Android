package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.TypeHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class TypeManager implements ServiceConnection {

    private Context context;
    private TypeHandler typeHandler;

    public TypeManager(Context context, TypeHandler typeHandler) {
        this.context = context;
        this.typeHandler = typeHandler;
    }

    public void getType(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.getType(context,typeHandler);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
