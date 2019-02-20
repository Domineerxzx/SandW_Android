package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.adapters.ShopBagAdapter;
import com.triplebro.aran.sandw.handlers.ShopBagHandler;
import com.triplebro.aran.sandw.managers.ShopBagManager;
import com.triplebro.aran.sandw.views.InnerListView;

public class ShopBagActivity extends Activity implements View.OnClickListener{

    private ImageView iv_close_shop_bag;
    private InnerListView ilv_shop_content;
    private ShopBagHandler shopBagHandler;
    private SharedPreferences sharedPreferences;
    private String session;
    private ShopBagManager shopBagManager;
    private RelativeLayout rl_shop_bag_empty;
    private TextView tv_little_num;
    private TextView tv_count_num;
    private Button bt_shop_bag_empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_bag);
        initView();
        initData();
        setOnClickListener();
    }

    private void initView() {
        iv_close_shop_bag = (ImageView) findViewById(R.id.iv_close_shop_bag);
        ilv_shop_content = (InnerListView) findViewById(R.id.ilv_shop_content);
        rl_shop_bag_empty = (RelativeLayout) findViewById(R.id.rl_shop_bag_empty);
        tv_little_num = (TextView) findViewById(R.id.tv_little_num);
        tv_count_num = (TextView) findViewById(R.id.tv_count_num);
        bt_shop_bag_empty = (Button) findViewById(R.id.bt_shop_bag_empty);
    }

    private void initData() {
        sharedPreferences = getSharedPreferences("session",MODE_PRIVATE);
        session = sharedPreferences.getString("session", null);
        shopBagHandler = new ShopBagHandler(this, ilv_shop_content,rl_shop_bag_empty,tv_little_num,tv_count_num);
        shopBagManager = new ShopBagManager(this, shopBagHandler, session);
        shopBagManager.showShopBag();
    }

    private void setOnClickListener() {
        iv_close_shop_bag.setOnClickListener(this);
        bt_shop_bag_empty.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_shop_bag:
                finish();
                break;
            case R.id.bt_shop_bag_empty:
                finish();
                break;
        }
    }
}
