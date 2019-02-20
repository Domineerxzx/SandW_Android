package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.ChangePassWordActivity;

public class ChangePassWordHandler extends Handler {
    private Context context;

    public ChangePassWordHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        ((ChangePassWordActivity) context).finish();
    }
}
