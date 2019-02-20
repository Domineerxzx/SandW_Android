package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.SelectAllActivity;
import com.triplebro.aran.sandw.managers.TypeOnClickManager;

public class TypeOnClickHandler extends Handler {

    private Context context;
    private TypeOnClickManager typeOnClickManager;

    public TypeOnClickHandler(Context context) {
        this.context = context;
    }

    public void setTypeOnClickManager(TypeOnClickManager typeOnClickManager) {
        this.typeOnClickManager = typeOnClickManager;
    }

    @Override
    public void handleMessage(Message msg) {

        String selectAllType = (String) msg.obj;
        Intent intent = new Intent(context, SelectAllActivity.class);
        intent.putExtra("data",selectAllType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        context.unbindService(typeOnClickManager);
    }
}
