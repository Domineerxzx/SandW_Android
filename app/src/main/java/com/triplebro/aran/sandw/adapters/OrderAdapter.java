package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.views.InnerListView;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<String> s;

    public OrderAdapter(Context context, List<String> s) {
        this.context = context;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final OrderAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new OrderAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_order, null);
            viewHolder.ilv_order = convertView.findViewById(R.id.ilv_order);
            viewHolder.tv_order_coast = convertView.findViewById(R.id.tv_order_coast);
            viewHolder.tv_order_count = convertView.findViewById(R.id.tv_order_count);
            viewHolder.tv_order_to_pay = convertView.findViewById(R.id.tv_order_to_pay);
            viewHolder.tv_order_to_cancel = convertView.findViewById(R.id.tv_order_to_cancel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tv_order_to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.tv_order_to_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    private class ViewHolder{
        private InnerListView ilv_order;
        private TextView tv_order_coast;
        private TextView tv_order_count;
        private TextView tv_order_to_pay;
        private TextView tv_order_to_cancel;
    }
}
