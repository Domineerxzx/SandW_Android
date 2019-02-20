package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.receivers.NetWorkChangeReceiver;

public class NetWorkDisconnectionActivity extends Activity {

    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter intentFilter;
    private NetWorkChangeReceiver netWorkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_disconnection);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog);
        builder.setTitle("网络异常");
        builder.setMessage("当前网络不可用，请检查好网络之后重试！！！");
        builder.setPositiveButton("重试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent("com.tts.triplebro.arans.networkreceiver");
                localBroadcastManager.sendBroadcast(intent);
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("退出程序", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
