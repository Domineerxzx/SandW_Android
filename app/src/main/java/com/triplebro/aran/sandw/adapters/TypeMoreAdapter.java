package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;

import java.util.List;

public class TypeMoreAdapter extends BaseAdapter {

    private Context context;
    private List<String> type_more;

    public TypeMoreAdapter(Context context, List<String> type_more) {
        this.context = context;
        this.type_more = type_more;
    }

    @Override
    public int getCount() {
        return type_more.size();
    }

    @Override
    public Object getItem(int position) {
        return type_more.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_type_more, null);
            viewHolder.tv_item_more_type = convertView.findViewById(R.id.tv_item_more_type);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_more_type.setText(type_more.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView tv_item_more_type;
    }
}
