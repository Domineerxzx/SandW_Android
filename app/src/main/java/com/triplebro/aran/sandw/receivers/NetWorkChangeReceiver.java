package com.triplebro.aran.sandw.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.triplebro.aran.sandw.activities.NetWorkDisconnectionActivity;

public class NetWorkChangeReceiver extends BroadcastReceiver {

    public NetWorkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI
                            || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Toast.makeText(context, "连上网络啦！！！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent netWorkDisconnection = new Intent(context, NetWorkDisconnectionActivity.class);
                    netWorkDisconnection.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(netWorkDisconnection);
                }
            }
        }
    }
}
