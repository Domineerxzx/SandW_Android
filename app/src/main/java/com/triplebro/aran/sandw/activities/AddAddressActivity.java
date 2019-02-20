package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.beans.AddAddressInfoBean;
import com.triplebro.aran.sandw.handlers.AddAddressHandler;
import com.triplebro.aran.sandw.managers.AddAddressManager;
import com.triplebro.aran.sandw.properties.AppProperties;

public class AddAddressActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_add_address;
    private EditText et_address_name;
    private EditText et_address_postcode;
    private EditText et_address_surname;
    private EditText et_address_detailed;
    private EditText et_address_telephone;
    private TextView tv_address_area;
    private TextView tv_address_area_province;
    private EditText et_address_area_city;
    private Button bt_add_address;
    private Button bt_address_area;
    private Button bt_address_area_province;
    private AddAddressHandler addAddressHandler;
    private String session;
    private SharedPreferences sessionInfo;
    private AddAddressManager addAddressManager;
    private AddAddressInfoBean addAddressInfoBean;
    private CheckBox cb_first_address;
    private TextView tv_first_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_add_address.setOnClickListener(this);
        tv_address_area.setOnClickListener(this);
        tv_address_area_province.setOnClickListener(this);
        bt_add_address.setOnClickListener(this);
        bt_address_area.setOnClickListener(this);
        bt_address_area_province.setOnClickListener(this);
    }

    private void initData() {
        sessionInfo = getSharedPreferences("session", MODE_PRIVATE);
        session = sessionInfo.getString("session", null);
        addAddressHandler = new AddAddressHandler(this);
    }

    private void initView() {
        iv_close_add_address = findViewById(R.id.iv_close_add_address);
        et_address_name = (EditText) findViewById(R.id.et_address_name);
        et_address_postcode = (EditText) findViewById(R.id.et_address_postcode);
        et_address_surname = (EditText) findViewById(R.id.et_address_surname);
        et_address_detailed = (EditText) findViewById(R.id.et_address_detailed);
        et_address_telephone = (EditText) findViewById(R.id.et_address_telephone);
        tv_address_area = (TextView) findViewById(R.id.tv_address_area);
        tv_address_area_province = (TextView) findViewById(R.id.tv_address_area_province);
        et_address_area_city = (EditText) findViewById(R.id.et_address_area_city);
        bt_add_address = (Button) findViewById(R.id.bt_add_address);
        bt_address_area = (Button) findViewById(R.id.bt_address_area);
        bt_address_area_province = (Button) findViewById(R.id.bt_address_area_province);
        cb_first_address = (CheckBox) findViewById(R.id.cb_first_address);
        tv_first_address = (TextView) findViewById(R.id.tv_first_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_add_address:
                finish();
                break;
            case R.id.tv_address_area:
            case R.id.bt_address_area:
                //TODO 添加Dialog，用来选择国家/地区
                AlertDialog.Builder chooseCountry = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Dialog);
                chooseCountry.setTitle("选择国家和地区");
                chooseCountry.setSingleChoiceItems(AppProperties.COUNTRY_OR_AREA, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_address_area.setText(AppProperties.COUNTRY_OR_AREA[which]);
                        dialog.dismiss();
                    }
                });
                chooseCountry.show();
                break;
            case R.id.tv_address_area_province:
            case R.id.bt_address_area_province:
                //TODO 添加Dialog，用来选择省/直辖市
                AlertDialog.Builder chooseProvince = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Dialog);
                chooseProvince.setTitle("选择省");
                chooseProvince.setSingleChoiceItems(AppProperties.PROVINCE, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_address_area_province.setText(AppProperties.PROVINCE[which]);
                        dialog.dismiss();
                    }
                });
                chooseProvince.show();
                break;
            case R.id.bt_add_address:
                //TODO 添加地址信息到服务器
                if(et_address_name.getText().toString().length() == 0||
                        et_address_postcode.getText().toString().length() == 0||
                        et_address_surname.getText().toString().length() == 0||
                        et_address_detailed.getText().toString().length() == 0||
                        et_address_telephone.getText().toString().length() == 0||
                        tv_address_area.getText().toString().length() == 0||
                        tv_address_area_province.getText().toString().length() == 0||
                        et_address_area_city.getText().toString().length() == 0){
                    Toast.makeText(this, "输入不能为空！！！", Toast.LENGTH_SHORT).show();
                    break;
                }
                addAddressInfoBean = new AddAddressInfoBean();
                addAddressInfoBean.setName(et_address_name.getText().toString().trim());
                addAddressInfoBean.setPostCode(et_address_postcode.getText().toString().trim());
                addAddressInfoBean.setSurName(et_address_surname.getText().toString().trim());
                addAddressInfoBean.setAddress(et_address_detailed.getText().toString().trim());
                addAddressInfoBean.setPhone(et_address_telephone.getText().toString().trim());
                addAddressInfoBean.setCountry(tv_address_area.getText().toString().trim());
                addAddressInfoBean.setProvince(tv_address_area_province.getText().toString().trim());
                addAddressInfoBean.setCity(et_address_area_city.getText().toString().trim());
                if (cb_first_address.isChecked()) {
                    addAddressInfoBean.setOption("true");
                } else {
                    addAddressInfoBean.setOption("false");
                }
                addAddressManager = new AddAddressManager(this, addAddressHandler, addAddressInfoBean, session);
                addAddressManager.addAddress();
                break;
            case R.id.tv_first_address:
                if (cb_first_address.isChecked()) {
                    cb_first_address.setChecked(false);
                } else {
                    cb_first_address.setChecked(true);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addAddressManager != null)
            unbindService(addAddressManager);
    }
}
