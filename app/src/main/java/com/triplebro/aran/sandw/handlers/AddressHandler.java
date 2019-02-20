package com.triplebro.aran.sandw.handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.triplebro.aran.sandw.adapters.AddressAdapter;
import com.triplebro.aran.sandw.beans.AddressInfoBean;

import java.util.List;


public class AddressHandler extends Handler {

    private Context context;
    private ListView lv_address;

    public AddressHandler(Context context, ListView lv_address) {
        this.context = context;
        this.lv_address = lv_address;
    }

    @Override
    public void handleMessage(Message msg) {
        AddressInfoBean addressInfoBean = (AddressInfoBean)msg.obj;
        List<AddressInfoBean.AddressBookListBean> addressBookList = addressInfoBean.getAddressBookList();
        AddressAdapter addressAdapter = new AddressAdapter(context, addressBookList);
        lv_address.setAdapter(addressAdapter);
    }
}
