package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.adapters.HistoryAdapter;
import com.triplebro.aran.sandw.adapters.MaybeAdapter;
import com.triplebro.aran.sandw.adapters.SaleAdapter;
import com.triplebro.aran.sandw.adapters.TypeContentAdapter;
import com.triplebro.aran.sandw.handlers.SearchHandler;
import com.triplebro.aran.sandw.managers.SearchManager;
import com.triplebro.aran.sandw.views.InnerListView;

import java.util.Map;

public class SearchActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_search;
    private RecyclerView rv_maybe_content;
    private InnerListView ilv_history_content;
    private InnerListView ilv_sale_content;
    private LinearLayout ll_search_title_f;
    private Button bt_search_title_f;
    private View v_search_title_f;
    private LinearLayout ll_search_title_m;
    private Button bt_search_title_m;
    private View v_search_title_m;
    private LinearLayout ll_search_title_c;
    private Button bt_search_title_c;
    private View v_search_title_c;
    private Button lastClickButton;
    private View lastClickView;
    private RelativeLayout rl_close_search;
    private LinearLayout ll_search_result;
    private LinearLayout ll_maybe;
    private SharedPreferences history;
    private SharedPreferences.Editor edit;
    private TextView tv_find;
    private EditText et_search_input;
    private RelativeLayout rl_history;
    private Map<String, String> historyAll;
    private HistoryAdapter historyAdapter;
    private SearchHandler searchHandler;
    private SearchManager searchManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        rl_close_search.setOnClickListener(this);
        iv_close_search.setOnClickListener(this);
        ll_search_title_f.setOnClickListener(this);
        bt_search_title_f.setOnClickListener(this);
        v_search_title_f.setOnClickListener(this);
        ll_search_title_m.setOnClickListener(this);
        bt_search_title_m.setOnClickListener(this);
        v_search_title_m.setOnClickListener(this);
        ll_search_title_c.setOnClickListener(this);
        bt_search_title_c.setOnClickListener(this);
        v_search_title_c.setOnClickListener(this);
        tv_find.setOnClickListener(this);
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_maybe_content.setLayoutManager(linearLayoutManager);
        searchHandler = new SearchHandler(this, rv_maybe_content, ilv_history_content, ilv_sale_content);
        searchManager = new SearchManager(this,searchHandler);
        ll_maybe.setVisibility(View.VISIBLE);
        ll_search_result.setVisibility(View.GONE);
        searchManager.getGoods();
        /*//searchManager.getBrands();
        SaleAdapter saleAdapter = new SaleAdapter(this);
        ilv_sale_content.setAdapter(saleAdapter);
        history = getSharedPreferences("history", MODE_PRIVATE);
        edit = history.edit();
        historyAll = (Map<String, String>) history.getAll();
        if(historyAll.size() == 0){
            //TODO 无数据，移除历史控件
            rl_history.setVisibility(View.GONE);
        }else{
            rl_history.setVisibility(View.VISIBLE);
            historyAdapter = new HistoryAdapter(this, historyAll);
            ilv_history_content.setAdapter(historyAdapter);
        }*/
    }

    private void initView() {
        ll_search_result = (LinearLayout) findViewById(R.id.ll_search_result);
        ll_maybe = (LinearLayout) findViewById(R.id.ll_maybe);
        rl_close_search = (RelativeLayout) findViewById(R.id.rl_close_search);
        iv_close_search = (ImageView) findViewById(R.id.iv_close_search);
        rv_maybe_content = (RecyclerView) findViewById(R.id.rv_maybe_content);
        ilv_history_content = (InnerListView) findViewById(R.id.ilv_history_content);
        ilv_sale_content = (InnerListView) findViewById(R.id.ilv_sale_content);
        ll_search_title_f = findViewById(R.id.ll_search_title_f);
        bt_search_title_f = findViewById(R.id.bt_search_title_f);
        v_search_title_f = findViewById(R.id.v_search_title_f);
        ll_search_title_m = findViewById(R.id.ll_search_title_m);
        bt_search_title_m = findViewById(R.id.bt_search_title_m);
        v_search_title_m = findViewById(R.id.v_search_title_m);
        ll_search_title_c = findViewById(R.id.ll_search_title_c);
        bt_search_title_c = findViewById(R.id.bt_search_title_c);
        v_search_title_c = findViewById(R.id.v_search_title_c);
        lastClickButton = bt_search_title_f;
        lastClickView = v_search_title_f;
        tv_find = (TextView) findViewById(R.id.tv_find);
        et_search_input = (EditText) findViewById(R.id.et_search_input);
        rl_history = (RelativeLayout) findViewById(R.id.rl_history);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_close_search:
            case R.id.iv_close_search:
                finish();
                break;
            case R.id.ll_search_title_f:
            case R.id.bt_search_title_f:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_search_title_f.setTextColor(Color.BLACK);
                v_search_title_f.setBackgroundColor(Color.BLACK);
                lastClickView = v_search_title_f;
                lastClickButton = bt_search_title_f;
                break;
            case R.id.ll_search_title_m:
            case R.id.bt_search_title_m:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_search_title_m.setTextColor(Color.BLACK);
                v_search_title_m.setBackgroundColor(Color.BLACK);
                lastClickView = v_search_title_m;
                lastClickButton = bt_search_title_m;
                break;
            case R.id.ll_search_title_c:
            case R.id.bt_search_title_c:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_search_title_c.setTextColor(Color.BLACK);
                v_search_title_c.setBackgroundColor(Color.BLACK);
                lastClickView = v_search_title_c;
                lastClickButton = bt_search_title_c;
                break;
            case R.id.tv_find:
                String search = et_search_input.getText().toString().trim();
                searchManager = new SearchManager(this, searchHandler, search);
                searchManager.find();
                break;
        }
    }
}
