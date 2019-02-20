package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.fragments.BottomFragment;
import com.triplebro.aran.sandw.fragmentReact.ReactFirstPageFragment;
import com.triplebro.aran.sandw.handlers.FirstPageHandler;
import com.triplebro.aran.sandw.managers.FirstPageManager;


public class MainActivity extends Activity implements View.OnClickListener{

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ImageView iv_search;
    ReactFirstPageFragment reactFirstPageFragment=new ReactFirstPageFragment();
    private FirstPageHandler firstPageHandler;
    private FirstPageManager firstPageManager;
    private ImageView iv_shopbag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        firstPageHandler = new FirstPageHandler(this,reactFirstPageFragment);
        firstPageManager = new FirstPageManager(this, firstPageHandler);
        firstPageManager.getGoodsInfo();
        transaction.replace(R.id.fl_bottom, new BottomFragment());
        transaction.commit();
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_search.setOnClickListener(this);
        iv_shopbag.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_shopbag = (ImageView) findViewById(R.id.iv_shopbag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                Intent search = new Intent(this, SearchActivity.class);
                startActivity(search);
                break;
            case R.id.iv_shopbag:
                SharedPreferences session = getSharedPreferences("session", MODE_PRIVATE);
                String session1 = session.getString("session", null);
                if (session1 == null) {
                    Toast.makeText(this, "未登录,还不能进入购物车，前去登录吧！！！", Toast.LENGTH_SHORT).show();
                } else{
                    Intent shopBag = new Intent(this, ShopBagActivity.class);
                    startActivity(shopBag);
                }
                break;
        }
    }
    public void Jump(){
        Intent intent = new Intent();
        startActivity(intent);
    }
}
