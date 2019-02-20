package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;

public class SaleAdapter extends BaseAdapter {
    private Context context;

    public SaleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SaleAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new SaleAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_sale, null);
            viewHolder.tv_sale = convertView.findViewById(R.id.tv_sale);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SaleAdapter.ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_sale;
    }
}
