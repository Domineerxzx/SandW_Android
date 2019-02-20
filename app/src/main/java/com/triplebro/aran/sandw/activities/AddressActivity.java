package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.adapters.AddressAdapter;
import com.triplebro.aran.sandw.beans.AddressInfoBean;
import com.triplebro.aran.sandw.handlers.AddressHandler;
import com.triplebro.aran.sandw.managers.AddressManager;

import java.util.ArrayList;

public class AddressActivity extends Activity implements View.OnClickListener{

    private ImageView iv_close_address;
    private Button bt_add_address;
    private ListView lv_address;
    private AddressHandler addressHandler;
    private SharedPreferences sessionInfo;
    private String session;
    private AddressManager addressManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();
        initData();
        setOnClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO 获取地址信息从服务器上
        addressManager = new AddressManager(this, addressHandler, session);
        addressManager.showAddress();
    }

    private void setOnClickListener() {
        iv_close_address.setOnClickListener(this);
        bt_add_address.setOnClickListener(this);
    }

    private void initData() {
        sessionInfo = getSharedPreferences("session", MODE_PRIVATE);
        session = sessionInfo.getString("session", null);
        addressHandler = new AddressHandler(this,lv_address);
        addressManager = new AddressManager(this, addressHandler, session);
    }

    private void initView() {
        iv_close_address = findViewById(R.id.iv_close_address);
        bt_add_address = findViewById(R.id.bt_add_address);
        lv_address = findViewById(R.id.lv_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_address:
                finish();
                break;
            case R.id.bt_add_address:
                Intent addAddress = new Intent(this, AddAddressActivity.class);
                startActivity(addAddress);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(addressManager);
    }
}
