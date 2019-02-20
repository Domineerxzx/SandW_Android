package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.triplebro.aran.sandw.R;

public class OrderActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lv_order;
    private ImageView iv_close_order;
    private LinearLayout ll_order_wait_pay;
    private Button bt_order_wait_pay;
    private View v_order_wait_pay;
    private LinearLayout ll_order_wait_get_goods;
    private Button bt_order_wait_get_goods;
    private View v_order_wait_get_goods;
    private LinearLayout ll_order_complete;
    private Button bt_order_complete;
    private View v_order_complete;
    private LinearLayout ll_order_cancel;
    private Button bt_order_cancel;
    private View v_order_cancel;
    private Button lastClickButton;
    private View lastClickView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_order.setOnClickListener(this);
        ll_order_wait_pay.setOnClickListener(this);
        bt_order_wait_pay.setOnClickListener(this);
        v_order_wait_pay.setOnClickListener(this);
        ll_order_wait_get_goods.setOnClickListener(this);
        bt_order_wait_get_goods.setOnClickListener(this);
        v_order_wait_get_goods.setOnClickListener(this);
        ll_order_complete.setOnClickListener(this);
        bt_order_complete.setOnClickListener(this);
        v_order_complete.setOnClickListener(this);
        ll_order_cancel.setOnClickListener(this);
        bt_order_cancel.setOnClickListener(this);
        v_order_cancel.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        lv_order = (ListView) findViewById(R.id.lv_order);
        iv_close_order = (ImageView) findViewById(R.id.iv_close_order);
        ll_order_wait_pay = (LinearLayout) findViewById(R.id.ll_order_wait_pay);
        bt_order_wait_pay = (Button) findViewById(R.id.bt_order_wait_pay);
        v_order_wait_pay = findViewById(R.id.v_order_wait_pay);
        ll_order_wait_get_goods = (LinearLayout) findViewById(R.id.ll_order_wait_get_goods);
        bt_order_wait_get_goods = (Button) findViewById(R.id.bt_order_wait_get_goods);
        v_order_wait_get_goods = findViewById(R.id.v_order_wait_get_goods);
        ll_order_complete = (LinearLayout) findViewById(R.id.ll_order_complete);
        bt_order_complete = (Button) findViewById(R.id.bt_order_complete);
        v_order_complete = findViewById(R.id.v_order_complete);
        ll_order_cancel = (LinearLayout) findViewById(R.id.ll_order_cancel);
        bt_order_cancel = (Button) findViewById(R.id.bt_order_cancel);
        v_order_cancel = findViewById(R.id.v_order_cancel);
        lastClickButton = bt_order_wait_pay;
        lastClickView = v_order_wait_pay;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_order:
                finish();
                break;
            case R.id.ll_order_wait_pay:
            case R.id.bt_order_wait_pay:
            case R.id.v_order_wait_pay:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_order_wait_pay.setTextColor(Color.BLACK);
                v_order_wait_pay.setBackgroundColor(Color.BLACK);
                lastClickView = v_order_wait_pay;
                lastClickButton = bt_order_wait_pay;
                break;
            case R.id.ll_order_wait_get_goods:
            case R.id.bt_order_wait_get_goods:
            case R.id.v_order_wait_get_goods:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_order_wait_get_goods.setTextColor(Color.BLACK);
                v_order_wait_get_goods.setBackgroundColor(Color.BLACK);
                lastClickView = v_order_wait_get_goods;
                lastClickButton = bt_order_wait_get_goods;
                break;
            case R.id.ll_order_complete:
            case R.id.bt_order_complete:
            case R.id.v_order_complete:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_order_complete.setTextColor(Color.BLACK);
                v_order_complete.setBackgroundColor(Color.BLACK);
                lastClickView = v_order_complete;
                lastClickButton = bt_order_complete;
                break;
            case R.id.ll_order_cancel:
            case R.id.bt_order_cancel:
            case R.id.v_order_cancel:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_order_cancel.setTextColor(Color.BLACK);
                v_order_cancel.setBackgroundColor(Color.BLACK);
                lastClickView = v_order_cancel;
                lastClickButton = bt_order_cancel;
                break;
        }
    }

    private void updateListView(BaseAdapter adapter){

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
