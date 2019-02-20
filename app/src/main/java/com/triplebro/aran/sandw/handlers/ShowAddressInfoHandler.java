package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.triplebro.aran.sandw.beans.ShowAddressInfoBean;

public class ShowAddressInfoHandler extends Handler {

    private Context context;
    private EditText et_address_name;
    private EditText et_address_postcode;
    private EditText et_address_surname;
    private EditText et_address_detailed;
    private EditText et_address_telephone;
    private TextView tv_address_area;
    private TextView tv_address_area_province;
    private EditText et_address_area_city;
    private CheckBox cb_first_address;

    public ShowAddressInfoHandler(Context context, EditText et_address_name, EditText et_address_postcode, EditText et_address_surname, EditText et_address_detailed, EditText et_address_telephone, TextView tv_address_area, TextView tv_address_area_province, EditText et_address_area_city,CheckBox cb_first_address) {
        this.context = context;
        this.et_address_name = et_address_name;
        this.et_address_postcode = et_address_postcode;
        this.et_address_surname = et_address_surname;
        this.et_address_detailed = et_address_detailed;
        this.et_address_telephone = et_address_telephone;
        this.tv_address_area = tv_address_area;
        this.tv_address_area_province = tv_address_area_province;
        this.et_address_area_city = et_address_area_city;
        this.cb_first_address = cb_first_address;
    }

    @Override
    public void handleMessage(Message msg) {
        ShowAddressInfoBean showAddressInfoBean = (ShowAddressInfoBean) msg.obj;
        ShowAddressInfoBean.AddressBookInfoBean addressBookInfo = showAddressInfoBean.getAddressBookInfo();
        et_address_name.setText(addressBookInfo.getName());
        String city = addressBookInfo.getCity();
        System.out.println(city);
        et_address_area_city.setText(addressBookInfo.getCity());
        et_address_detailed.setText(addressBookInfo.getAddress());
        et_address_postcode.setText(addressBookInfo.getPostCode());
        et_address_surname.setText(addressBookInfo.getSurName());
        et_address_telephone.setText(addressBookInfo.getPhone());
        tv_address_area.setText(addressBookInfo.getCountry());
        tv_address_area_province.setText(addressBookInfo.getProvince());
        cb_first_address.setChecked(showAddressInfoBean.isOption());
    }
}
