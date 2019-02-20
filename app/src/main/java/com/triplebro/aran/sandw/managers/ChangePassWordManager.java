package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.ChangePassWordHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class ChangePassWordManager implements ServiceConnection {

    private Context context;
    private ChangePassWordHandler changePassWordHandler;
    private String old_password;
    private String new_password;
    private String session;

    public ChangePassWordManager(Context context, ChangePassWordHandler changePassWordHandler, String old_password, String new_password, String session) {
        this.context = context;
        this.changePassWordHandler = changePassWordHandler;
        this.old_password = old_password;
        this.new_password = new_password;
        this.session = session;
    }

    public void changePassword(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.changePassword(context,changePassWordHandler,old_password,new_password,session);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
