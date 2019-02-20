package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.triplebro.aran.sandw.activities.RegisterActivity;

public class RegisterHandler extends Handler {
    private Context context;

    public RegisterHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        String res = (String) msg.obj;
        ((RegisterActivity) context).finish();
    }
}
