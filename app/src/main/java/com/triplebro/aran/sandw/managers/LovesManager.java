package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.LovesHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class LovesManager implements ServiceConnection {

    private Context context;
    private LovesHandler lovesHandler;
    private String session;
    private String commodityId;
    private int what;

    public LovesManager(Context context, LovesHandler lovesHandler, String session) {
        this.context = context;
        this.lovesHandler = lovesHandler;
        this.session = session;
        lovesHandler.setLovesManager(this);
    }

    public LovesManager(Context context, LovesHandler lovesHandler, String session,String commodityId) {
        this.context = context;
        this.lovesHandler = lovesHandler;
        this.session = session;
        this.commodityId = commodityId;
        lovesHandler.setLovesManager(this);
    }

    public void getLovesList(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        what = 1;
    }

    public void addLovesList(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        what = 2;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        switch (what){
            case 1:
                myBinder.getLovesList(context,lovesHandler,session);
                break;
            case 2:
                myBinder.addLovesList(context,lovesHandler,session,commodityId);
                break;
            case 3:
                break;
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
