package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.AddAddressActivity;

public class AddAddressHandler extends Handler {

    private Context context;

    public AddAddressHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        ((AddAddressActivity)context).finish();
    }
}
