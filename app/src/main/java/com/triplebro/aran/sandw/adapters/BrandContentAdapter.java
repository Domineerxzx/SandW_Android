package com.triplebro.aran.sandw.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.triplebro.aran.sandw.R;
import com.triplebro.aran.sandw.beans.BrandInfo;
import com.triplebro.aran.sandw.handlers.BrandOnClickHandler;
import com.triplebro.aran.sandw.managers.BrandOnClickManager;
import com.triplebro.aran.sandw.views.InnerListView;

import java.util.List;

public class BrandContentAdapter extends BaseAdapter {

    private Context context;
    private List<BrandInfo.BrandListBean.ClassValueBean> classValueBeans;

    public BrandContentAdapter(Context context,List<BrandInfo.BrandListBean.ClassValueBean> classValueBeans) {
        this.context = context;
        this.classValueBeans = classValueBeans;
    }

    @Override
    public int getCount() {
        return classValueBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return classValueBeans.get(position);
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
            convertView = View.inflate(context, R.layout.item_brand, null);
            viewHolder.tv_item_brand = convertView.findViewById(R.id.tv_item_brand);
            viewHolder.ll_brand_more = convertView.findViewById(R.id.ll_brand_more);
            viewHolder.lv_brand_more = convertView.findViewById(R.id.lv_brand_more);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item_brand.setText(classValueBeans.get(position).getCharClassName());
        BrandMoreAdapter brandMoreAdapter = new BrandMoreAdapter(context,classValueBeans.get(position).getCharClassValue());
        viewHolder.lv_brand_more.setAdapter(brandMoreAdapter);
        final List<String> charClassValue = classValueBeans.get(position).getCharClassValue();
        viewHolder.lv_brand_more.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 跳转页面，跳转到对应的品牌商品展示页
                BrandOnClickHandler brandOnClickHandler = new BrandOnClickHandler(context);
                BrandOnClickManager brandOnClickManager = new BrandOnClickManager(context, charClassValue.get(position), brandOnClickHandler);
                brandOnClickManager.startSelectAllActivity();
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView tv_item_brand;
        LinearLayout ll_brand_more;
        InnerListView lv_brand_more;
    }
}
