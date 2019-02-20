package com.triplebro.aran.sandw.managers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.triplebro.aran.sandw.handlers.SearchHandler;
import com.triplebro.aran.sandw.services.NetworkCommunicationService;

public class SearchManager implements ServiceConnection {

    private Context context;
    private SearchHandler searchHandler;
    private String find;
    private int functionType;

    public SearchManager(Context context, SearchHandler searchHandler) {
        this.context = context;
        this.searchHandler = searchHandler;
    }

    public SearchManager(Context context, SearchHandler searchHandler, String find) {
        this.context = context;
        this.searchHandler = searchHandler;
        this.find = find;
    }

    public void find(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        functionType = 1;
    }

    public void getGoods(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        functionType = 2;
    }

    public void getBrands(){
        Intent intent = new Intent(context, NetworkCommunicationService.class);
        context.bindService(intent,this,Context.BIND_AUTO_CREATE);
        functionType = 3;
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        NetworkCommunicationService.MyBinder myBinder = (NetworkCommunicationService.MyBinder) service;
        switch (functionType){
            case 1:
                myBinder.find(context,searchHandler,find);
                break;
            case 2:
                myBinder.getGoods(context,searchHandler);
                break;
            case 3:
                myBinder.getBrands(context,searchHandler);
                break;
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
