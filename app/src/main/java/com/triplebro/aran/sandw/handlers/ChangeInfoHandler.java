package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.ChangeUserInfoActivity;

public class ChangeInfoHandler extends Handler {

    private Context context;

    public ChangeInfoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        ((ChangeUserInfoActivity)context).finish();
    }
}
