package com.triplebro.aran.sandw.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.activities.BrandListActivity;
import com.triplebro.aran.sandw.fragmentReact.ReactBrandFragment;
import com.triplebro.aran.sandw.handlers.BrandHandler;
import com.triplebro.aran.sandw.managers.BrandManager;


public class BrandFragment extends Fragment implements View.OnClickListener {

    private View fragment_brand;
    private TextView tv_title;
    private TextView tv_brand_list;
    private RelativeLayout rl_brand_list;
    private ImageView iv_brand_list;
    private FrameLayout fl_brand_content;
    private ReactBrandFragment reactBrandFragment = new ReactBrandFragment();
    private BrandHandler brandHandler;
    private BrandManager brandManager;
    private Button lastClickButton;
    private View lastClickView;
    private LinearLayout ll_brand_title_f;
    private LinearLayout ll_brand_title_m;
    private LinearLayout ll_brand_title_c;
    private Button bt_brand_title_f;
    private Button bt_brand_title_m;
    private Button bt_brand_title_c;
    private View v_brand_title_f;
    private View v_brand_title_m;
    private View v_brand_title_c;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_brand = inflater.inflate(R.layout.fragment_brand, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_brand;
    }

    private void setOnClickListener() {
        tv_brand_list.setOnClickListener(this);
        rl_brand_list.setOnClickListener(this);
        iv_brand_list.setOnClickListener(this);
        ll_brand_title_f.setOnClickListener(this);
        ll_brand_title_m.setOnClickListener(this);
        ll_brand_title_c.setOnClickListener(this);
        bt_brand_title_f.setOnClickListener(this);
        bt_brand_title_m.setOnClickListener(this);
        bt_brand_title_c.setOnClickListener(this);
        v_brand_title_f.setOnClickListener(this);
        v_brand_title_m.setOnClickListener(this);
        v_brand_title_c.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText(R.string.title_brand);
        brandHandler = new BrandHandler(getActivity(), reactBrandFragment);
        brandManager = new BrandManager(getActivity(), brandHandler);
        brandManager.getGoodsInfo();
    }

    private void initView() {
        tv_title = getActivity().findViewById(R.id.tv_title);
        tv_brand_list = (TextView) fragment_brand.findViewById(R.id.tv_brand_list);
        rl_brand_list = (RelativeLayout) fragment_brand.findViewById(R.id.rl_brand_list);
        iv_brand_list = (ImageView) fragment_brand.findViewById(R.id.iv_brand_list);
        fl_brand_content = (FrameLayout) fragment_brand.findViewById(R.id.fl_brand_content);
        ll_brand_title_f = (LinearLayout) fragment_brand.findViewById(R.id.ll_brand_title_f);
        ll_brand_title_m = (LinearLayout) fragment_brand.findViewById(R.id.ll_brand_title_m);
        ll_brand_title_c = (LinearLayout) fragment_brand.findViewById(R.id.ll_brand_title_c);
        bt_brand_title_f = (Button) fragment_brand.findViewById(R.id.bt_brand_title_f);
        bt_brand_title_m = (Button) fragment_brand.findViewById(R.id.bt_brand_title_m);
        bt_brand_title_c = (Button) fragment_brand.findViewById(R.id.bt_brand_title_c);
        v_brand_title_f = fragment_brand.findViewById(R.id.v_brand_title_f);
        v_brand_title_m = fragment_brand.findViewById(R.id.v_brand_title_m);
        v_brand_title_c = fragment_brand.findViewById(R.id.v_brand_title_c);
        lastClickButton = bt_brand_title_f;
        lastClickView = v_brand_title_f;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_brand_list:
            case R.id.rl_brand_list:
            case R.id.iv_brand_list:
                Intent brandList = new Intent(getActivity(), BrandListActivity.class);
                getActivity().startActivity(brandList);
                break;
            case R.id.ll_brand_title_f:
            case R.id.bt_brand_title_f:
            case R.id.v_brand_title_f:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_brand_title_f.setTextColor(Color.BLACK);
                v_brand_title_f.setBackgroundColor(Color.BLACK);
                lastClickView = v_brand_title_f;
                lastClickButton = bt_brand_title_f;
                break;
            case R.id.ll_brand_title_m:
            case R.id.bt_brand_title_m:
            case R.id.v_brand_title_m:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_brand_title_m.setTextColor(Color.BLACK);
                v_brand_title_m.setBackgroundColor(Color.BLACK);
                lastClickView = v_brand_title_m;
                lastClickButton = bt_brand_title_m;
                break;
            case R.id.ll_brand_title_c:
            case R.id.bt_brand_title_c:
            case R.id.v_brand_title_c:
                lastClickButton.setTextColor(Color.GRAY);
                lastClickView.setBackgroundColor(Color.GRAY);
                bt_brand_title_c.setTextColor(Color.BLACK);
                v_brand_title_c.setBackgroundColor(Color.BLACK);
                lastClickView = v_brand_title_c;
                lastClickButton = bt_brand_title_c;
                break;
        }
    }
}
