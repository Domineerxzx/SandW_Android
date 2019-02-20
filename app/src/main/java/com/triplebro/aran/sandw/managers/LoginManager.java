package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.triplebro.aran.sandw.handlers.LoginHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;


public class LoginManager implements ServiceConnection{

    private Context context;
    private LoginHandler loginHandler;
    private String email;
    private String password;

    public LoginManager(Context context, LoginHandler loginHandler, String email, String password) {
        this.context = context;
        this.loginHandler = loginHandler;
        this.email = email;
        this.password = password;
    }

    public void login() {
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.login(context,loginHandler,email,password);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
