package com.triplebro.aran.sandw.handlers;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.fragmentReact.ReactLovesFragment;
import com.triplebro.aran.sandw.managers.LovesManager;
import com.triplebro.aran.sandw.modules.AransPackage;
import com.triplebro.aran.sandw.properties.AppProperties;

public class LovesHandler extends Handler {
    private Context context;
    private ReactLovesFragment reactLovesFragment;
    private LovesManager lovesManager;

    public LovesHandler(Context context) {
        this.context = context;
    }

    public LovesHandler(Context context, ReactLovesFragment reactLovesFragment) {
        this.context = context;
        this.reactLovesFragment = reactLovesFragment;
    }

    public void setLovesManager(LovesManager lovesManager) {
        this.lovesManager = lovesManager;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case AppProperties.LOVES_LIST_SHOW:
                String lovesList = (String) msg.obj;
                AransPackage aransPackage = ((ReactLovesFragment)reactLovesFragment).getAransPackage();
                aransPackage.setData(lovesList);
                FragmentTransaction transaction = ((Activity) context).getFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_content, reactLovesFragment);
                transaction.commit();
                break;
            case AppProperties.LOVES_LIST_ADD:
                Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case AppProperties.LOVES_LIST_EMPTY:

                break;
        }

    }
}
