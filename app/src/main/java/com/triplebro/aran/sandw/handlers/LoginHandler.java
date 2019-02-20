package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.LoginActivity;

public class LoginHandler extends Handler {

    private Context context;

    public LoginHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        String res = (String) msg.obj;
        ((LoginActivity)context).finish();
    }
}
