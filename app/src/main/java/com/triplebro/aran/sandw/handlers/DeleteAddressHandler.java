package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.ChangeAddressActivity;

public class DeleteAddressHandler extends Handler {

    private Context context;

    public DeleteAddressHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        ((ChangeAddressActivity)context).finish();
    }
}
