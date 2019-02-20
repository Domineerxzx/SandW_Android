package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;

import java.util.Map;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private Map<String, String> historyAll;

    public HistoryAdapter(Context context, Map<String, String> historyAll) {
        this.context = context;
        this.historyAll = historyAll;
    }

    @Override
    public int getCount() {
        return historyAll.size();
    }

    @Override
    public Object getItem(int position) {
        return historyAll.get("history"+(position+1));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HistoryAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new HistoryAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.item_history, null);
            viewHolder.tv_history = convertView.findViewById(R.id.tv_history);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HistoryAdapter.ViewHolder) convertView.getTag();
        }
        String history = historyAll.get("history" + (position+1));
        viewHolder.tv_history.setText(history);
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_history;
    }
}
