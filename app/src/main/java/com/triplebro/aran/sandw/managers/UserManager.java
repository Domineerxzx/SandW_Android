package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.triplebro.aran.sandw.handlers.UserHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class UserManager implements ServiceConnection{

    private Context context;
    private UserHandler userHandler;
    private String session;
    private int messageWhat;

    public UserManager(Context context, UserHandler userHandler, String session, int messageWhat) {
        this.context = context;
        this.userHandler = userHandler;
        this.session = session;
        this.messageWhat = messageWhat;
    }

    public void showUserInfo() {
        Intent show = new Intent(context, NetworkCommunicationService.class);
        context.bindService(show,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.showUserInfo(context,userHandler,session,messageWhat);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
