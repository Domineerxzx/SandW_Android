package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.ChangeInfoHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class ChangeInfoManager implements ServiceConnection {

    private Context context;
    private ChangeInfoHandler changeInfoHandler;
    private String nickname;
    private String email;
    private String birthday;
    private String sex;
    private String session;

    public ChangeInfoManager(Context context, ChangeInfoHandler changeInfoHandler, String nickname, String email, String birthday, String sex, String session) {
        this.context = context;
        this.changeInfoHandler = changeInfoHandler;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.sex = sex;
        this.session = session;
    }

    public void changeInfo(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        myBinder.changeInfo(context,changeInfoHandler,nickname,email,birthday,sex,session);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
