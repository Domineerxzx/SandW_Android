package com.triplebro.aran.sandw.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.beans.ChangeAddressInfoBean;
import com.triplebro.aran.sandw.handlers.ChangeAddressHandler;
import com.triplebro.aran.sandw.handlers.DeleteAddressHandler;
import com.triplebro.aran.sandw.handlers.ShowAddressInfoHandler;
import com.triplebro.aran.sandw.managers.ChangeAddressManager;
import com.triplebro.aran.sandw.managers.DeleteAddressManager;
import com.triplebro.aran.sandw.managers.ShowAddressInfoManager;
import com.triplebro.aran.sandw.properties.AppProperties;

public class ChangeAddressActivity extends Activity implements View.OnClickListener {

    private ImageView iv_close_change_address;
    private EditText et_address_name;
    private EditText et_address_postcode;
    private EditText et_address_surname;
    private EditText et_address_detailed;
    private EditText et_address_telephone;
    private TextView tv_address_area;
    private TextView tv_address_area_province;
    private EditText et_address_area_city;
    private Button bt_change_address;
    private Button bt_address_area;
    private Button bt_address_area_province;
    private Button bt_delete_address;
    private CheckBox cb_first_address;
    private TextView tv_first_address;
    private ShowAddressInfoHandler showAddressInfoHandler;
    private ShowAddressInfoManager showAddressInfoManager;
    private ChangeAddressHandler changeAddressHandler;
    private ChangeAddressManager changeAddressManager;
    private String session;
    private SharedPreferences sessionInfo;
    private ChangeAddressInfoBean changeAddressInfoBean;
    private DeleteAddressHandler deleteAddressHandler;
    private DeleteAddressManager deleteAddressManager;
    private String address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        initView();
        initData();
        setOnClickListener();
    }

    private void setOnClickListener() {
        iv_close_change_address.setOnClickListener(this);
        tv_address_area.setOnClickListener(this);
        tv_address_area_province.setOnClickListener(this);
        bt_change_address.setOnClickListener(this);
        bt_delete_address.setOnClickListener(this);
        bt_address_area.setOnClickListener(this);
        bt_address_area_province.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        address_id = intent.getStringExtra("address_id");
        showAddressInfoHandler = new ShowAddressInfoHandler(this, et_address_name, et_address_postcode, et_address_surname, et_address_detailed, et_address_telephone, tv_address_area, tv_address_area_province, et_address_area_city, cb_first_address);
        showAddressInfoManager = new ShowAddressInfoManager(this, showAddressInfoHandler, address_id);
        showAddressInfoManager.showAddressInfo();
        changeAddressHandler = new ChangeAddressHandler(this);
        sessionInfo = getSharedPreferences("session", MODE_PRIVATE);
        session = sessionInfo.getString("session", null);
        deleteAddressHandler = new DeleteAddressHandler(this);
    }

    private void initView() {
        iv_close_change_address = findViewById(R.id.iv_close_change_address);
        et_address_name = (EditText) findViewById(R.id.et_address_name);
        et_address_postcode = (EditText) findViewById(R.id.et_address_postcode);
        et_address_surname = (EditText) findViewById(R.id.et_address_surname);
        et_address_detailed = (EditText) findViewById(R.id.et_address_detailed);
        et_address_telephone = (EditText) findViewById(R.id.et_address_telephone);
        tv_address_area = (TextView) findViewById(R.id.tv_address_area);
        tv_address_area_province = (TextView) findViewById(R.id.tv_address_area_province);
        et_address_area_city = (EditText) findViewById(R.id.et_address_area_city);
        bt_change_address = (Button) findViewById(R.id.bt_change_address);
        bt_address_area = (Button) findViewById(R.id.bt_address_area);
        bt_address_area_province = (Button) findViewById(R.id.bt_address_area_province);
        bt_delete_address = (Button) findViewById(R.id.bt_delete_address);
        cb_first_address = (CheckBox) findViewById(R.id.cb_first_address);
        tv_first_address = (TextView) findViewById(R.id.tv_first_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_change_address:
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
                break;
            case R.id.bt_change_address:
                //TODO 修改地址信息到服务器
                changeAddressInfoBean = new ChangeAddressInfoBean();
                changeAddressInfoBean.setName(et_address_name.getText().toString().trim());
                changeAddressInfoBean.setPostCode(et_address_postcode.getText().toString().trim());
                changeAddressInfoBean.setSurName(et_address_surname.getText().toString().trim());
                changeAddressInfoBean.setAddress(et_address_detailed.getText().toString().trim());
                changeAddressInfoBean.setPhone(et_address_telephone.getText().toString().trim());
                changeAddressInfoBean.setCountry(tv_address_area.getText().toString().trim());
                changeAddressInfoBean.setProvince(tv_address_area_province.getText().toString().trim());
                changeAddressInfoBean.setCity(et_address_area_city.getText().toString().trim());
                changeAddressInfoBean.setAddressId(address_id);
                if (cb_first_address.isChecked()) {
                    changeAddressInfoBean.setOption("true");
                } else {
                    changeAddressInfoBean.setOption("false");
                }
                changeAddressManager = new ChangeAddressManager(this, changeAddressHandler, changeAddressInfoBean, session);
                changeAddressManager.changeAddress();
                break;
            case R.id.bt_delete_address:
                //TODO 通知服务器删除地址信息
                deleteAddressManager = new DeleteAddressManager(this, deleteAddressHandler, session, address_id);
                deleteAddressManager.deleteAddress();
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
        unbindService(showAddressInfoManager);
        if (changeAddressManager != null) {
            unbindService(changeAddressManager);
        }
        if (deleteAddressManager != null) {
            unbindService(deleteAddressManager);
        }
    }
}
