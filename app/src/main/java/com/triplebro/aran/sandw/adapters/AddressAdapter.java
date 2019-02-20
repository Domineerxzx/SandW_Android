package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.activities.ChangeAddressActivity;
import com.triplebro.aran.sandw.beans.AddressInfoBean;

import java.util.List;

public class AddressAdapter extends BaseAdapter {

    private Context context;
    private List<AddressInfoBean.AddressBookListBean> addressInfoList;

    public AddressAdapter(Context context, List<AddressInfoBean.AddressBookListBean> addressInfoList) {
        this.context = context;
        this.addressInfoList = addressInfoList;
    }

    @Override
    public int getCount() {
        return addressInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AddressAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new AddressAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_address, null);
            viewHolder.tv_address_name = convertView.findViewById(R.id.tv_address_name);
            viewHolder.tv_address_family_name = convertView.findViewById(R.id.tv_address_family_name);
            viewHolder.tv_address_detailed = convertView.findViewById(R.id.tv_address_detailed);
            viewHolder.tv_address_city = convertView.findViewById(R.id.tv_address_city);
            viewHolder.tv_address_postcode = convertView.findViewById(R.id.tv_address_postcode);
            viewHolder.tv_address_area = convertView.findViewById(R.id.tv_address_area);
            viewHolder.bt_change_address_info = convertView.findViewById(R.id.bt_change_address_info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AddressAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_address_name.setText(addressInfoList.get(position).getName());
        viewHolder.tv_address_family_name.setText(addressInfoList.get(position).getSurName());
        viewHolder.tv_address_area.setText(addressInfoList.get(position).getCountry());
        viewHolder.tv_address_city.setText(addressInfoList.get(position).getCity());
        viewHolder.tv_address_detailed.setText(addressInfoList.get(position).getAddress());
        viewHolder.tv_address_postcode.setText(addressInfoList.get(position).getPostCode());
        viewHolder.bt_change_address_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChangeAddressActivity.class);
                String address_id = String.valueOf(addressInfoList.get(position).getId());
                intent.putExtra("address_id",address_id);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView tv_address_name;
        TextView tv_address_family_name;
        TextView tv_address_detailed;
        TextView tv_address_city;
        TextView tv_address_postcode;
        TextView tv_address_area;
        Button bt_change_address_info;
    }
}
