package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.triplebro.aran.sandw.handlers.RegisterHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class RegisterManager implements ServiceConnection{

    private Context context;
    private RegisterHandler registerHandler;
    private String nickname;
    private String email;
    private String password;

    public RegisterManager(Context context, RegisterHandler registerHandler, String nickname, String email, String password) {
        this.context = context;
        this.registerHandler = registerHandler;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public void register() {
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.register(context,registerHandler,nickname,email,password);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

}
