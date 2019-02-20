package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.triplebro.aran.sandw.activities.SelectAllActivity;
import com.triplebro.aran.sandw.managers.SelectAllManager;

public class SelectAllHandler extends Handler {

    private Context context;
    private SelectAllManager selectAllManager;

    public SelectAllHandler(Context context) {
        this.context = context;
    }

    public SelectAllManager getSelectAllManager() {
        return selectAllManager;
    }

    public void setSelectAllManager(SelectAllManager selectAllManager) {
        this.selectAllManager = selectAllManager;
    }

    @Override
    public void handleMessage(Message msg) {
        String selectAll = (String) msg.obj;
        context.unbindService(selectAllManager);
        Intent intent = new Intent(context, SelectAllActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data",selectAll);
        context.startActivity(intent);
    }
}
